package core;

/**
 * @author tliu@u-cergy.fr
 */
public class Canton {
	private int id;
	private int startPoint;
	private int length;
	private int fork;
	private Train occupyingTrain = null;

	public Canton(int id, int length, int startPoint) {
		this.id = id;
		this.length = length;
		this.startPoint = startPoint;
	}
	
	/**
	 * @author Karim
	 *
	 */
	public Canton(int id, int length, int startPoint, int fork) {
		this.id = id;
		this.length = length;
		this.startPoint = startPoint;
		this.fork = fork;
	}

	public int getLength() {
		return length;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public int getEndPoint() {
		return startPoint + length;
	}

	//si un deja present dans un canton on attend jusqu'à ce qu'il en sorte 
	//puis on actualise le canton courrant du train 
	public synchronized void enter(Train train) {
		if (occupyingTrain != null) {
			System.out.println(toString() + " occupied !");
			// Train stopped just before canton start point !
			train.setPosition(startPoint - 1);
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}

//		System.out.println("Canton changed successfully");
		Canton oldCanton = train.getCurrentCanton();
		train.setCurrentCanton(this);
		train.updatePosition();

		oldCanton.exit();
		occupyingTrain = train;

	}

	//pour dire que le canton a été liberer
	public synchronized void exit() {
		occupyingTrain = null;
		notify();
//		System.out.println("Canton freed !");
	}

	public boolean isFree() {
		return occupyingTrain == null;
	}

	@Override
	public String toString() {
		return "Canton [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

}
