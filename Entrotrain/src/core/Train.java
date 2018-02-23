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
	private Station currentStation;
	private int currentPassengers = 0;  //train leaves garage with 0 passengers
	private ArrayList<Passenger> trainPassengers = new ArrayList<Passenger>();
	private int maxPassenger;

	/**
	 * Distance per time unit.
	 */
	private int speed;
	private boolean hasArrived = false;


	public Train(Line line, Canton startCanton , Station startStation , int speed , int currentPassengers , int maxPassenger) {
		this.line = line;
		
		currentCanton = startCanton;
		currentCanton.enter(this);
		
		currentStation = startStation;
		
		this.currentStation = startStation;
		this.currentCanton = startCanton;
		
		this.speed = speed;
		
		this.currentPassengers = currentPassengers;
		this.maxPassenger = maxPassenger;
	}

	

	public int getPosition() {
		return position;
	}



	public void setPosition(int position) {
		this.position = position;
	}



	public Line getLine() {
		return line;
	}



	public void setLine(Line line) {
		this.line = line;
	}



	public Canton getCurrentCanton() {
		return currentCanton;
	}



	public void setCurrentCanton(Canton currentCanton) {
		this.currentCanton = currentCanton;
	}



	public Station getCurrentStation() {
		return currentStation;
	}



	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}



	public int getCurrentPassengers() {
		return currentPassengers;
	}



	public void setCurrentPassengers(int currentPassengers) {
		this.currentPassengers = currentPassengers;
	}



	public ArrayList<Passenger> getTrainPassengers() {
		return trainPassengers;
	}



	public void setTrainPassengers(ArrayList<Passenger> trainPassengers) {
		this.trainPassengers = trainPassengers;
	}



	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public boolean isHasArrived() {
		return hasArrived;
	}



	public void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}



	public int getMaxPassenger() {
		return maxPassenger;
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
	 * and return the list of passenger who are leave the train
	 */
	
	/*public ArrayList<Passenger> trainUnboarding(Station station) {
		int index;
		ArrayList<Passenger> ArrivalPassenger = new ArrayList<Passenger>();
		for(index = 0; index<this.trainPassengers.size();index++) {
			if(this.trainPassengers.get(index).getIdArrival() == station.getIdStation()){
				ArrivalPassenger.add(this.trainPassengers.get(index));
				this.trainPassengers.remove(index);
			}
		}
		return ArrivalPassenger;
	}*/
	
	/***
	 * How to deal with a passenger catching his train.
	 */
	public void trainBoarding(Station station) {
		if(this.maxPassenger == this.trainPassengers.size()) {
			System.out.println("Train is full capacity :"+this.trainPassengers.size()+"\n");
		}else {
			int indexPassengers = 0;
			while(this.maxPassenger >= this.currentPassengers && indexPassengers < station.getPassengers().size()) {
				this.trainPassengers.add(station.getPassengers().get(indexPassengers));
				indexPassengers++;
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