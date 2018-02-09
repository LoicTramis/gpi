package core;

import exception.TerminusException;
import gui.SimulationGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author cyril belmonte
 */
public class Train extends Thread {
	private volatile int position = 0;
	private Line line;
	private Canton currentCanton;
	private int currentCapacity = 0;  //train leaves garage with 0 passengers
	private int maxCapacity;
	private Station currentStation = null ; //garage isnt a station
	private List<Passenger> trainPassengers = new ArrayList<Passenger>();
	private int maxPassenger;
	private int currentPassengers;


	/**
	 * Distance per time unit.
	 */
	private int speed;
	private boolean hasArrived = false;


	
	/**
	 * @param line
	 * @param startCanton
	 * @param speed
	 * @param currentPassenger
	 * @param maxPassenger
	 */
	
	public Train(Line line, Canton startCanton, int speed, int currentPassenger , int maxPassenger) {
		this.line = line;
		currentCanton = startCanton;
		currentCanton.enter(this);
		this.maxCapacity = maxCapacity;
		this.speed = speed;
		this.currentCanton = currentCanton;
		this.maxPassenger = maxPassenger;
	}

	public int getMaxPassenger() {
		return maxPassenger;
	}

	public void setMaxPassenger(int maxPassenger) {
		this.maxPassenger = maxPassenger;
	}

	public int getCurrentPassengers() {
		return currentPassengers;
	}

	public void setCurrentPassengers(int currentPassengers) {
		this.currentPassengers = currentPassengers;
	}

	public int getPosition() {
		return position;
	}

	public Canton getCurrentCanton() {
		return currentCanton;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setCurrentCanton(Canton currentCanton) {
		this.currentCanton = currentCanton;
	}
	
	public int getCurrentCapacity() {
		return currentCapacity;
	}
	
	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}


	@Override
	public void run() {
		while (!hasArrived) {
			try {
				sleep(SimulationGUI.TIME_UNIT);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			if (position + speed >= currentCanton.getEndPoint()) {
				try {
					Canton nextCanton = line.getCantonByPosition(position + speed);
					nextCanton.enter(this);
				} catch (TerminusException e) {
					hasArrived = true;
					position = line.getTotalLenght();
				}
			} else {
				updatePosition();
			}
		}
		currentCanton.exit();
	}

	
	/***
	 * How to deal with the situation of a passenger arriving to his destination
	 */
	public void trainUnboarding() {
		for(Iterator iter = trainPassengers.iterator(); iter.hasNext();){
			Passenger p1 = (Passenger) iter.next();
			if (p1.getStationOfArrival().equals(this.currentStation)) {
				iter.remove(); //p1 got off the train and arrived at his destination
				this.currentCapacity--;
			}
		}
	}
	
	
	/***
	 * How to deal with a passenger catching his train.
	 */
	public void trainBoarding() {
		for(Iterator iter = this.currentStation.getPassengers().iterator(); iter.hasNext();){
			Passenger p1 = (Passenger) iter.next();
			if (p1.getStationOfDeparture().equals(this.currentStation) && this.currentCapacity < this.maxCapacity) {
				iter.remove(); //p1 boards on the train and leaves the current station
				this.currentStation.setPassenger(this.currentStation.getPassenger()-1); //Station loses 1 passenger since he boarded on the train
				this.trainPassengers.add(p1);
			}
		}	
	}
	
	
	
	@Override
	public String toString() {
		return "Train [speed=" + speed + "]";
	}

	public void updatePosition() {
		position += speed;
	}

}