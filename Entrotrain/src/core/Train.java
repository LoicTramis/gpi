package core;

import exception.TerminusException;
import gui.SimulationGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * @author cyril belmonte
 */
public class Train extends Thread {
	private volatile int position = 0;
	private Line line;
	private Canton currentCanton;
	private Station currentStation;
	private Incident incident = null;
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
	
	public void setIncident(Incident incident){
		this.incident=incident;
	}
	
	public void generateIncident(Train train){
		Random x = new Random();
		Incident train_problem = new Incident(x.nextInt(10));
		train.setIncident(train_problem);
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
				if (hasEnteredStation(this,line.getStations())) {
					trainUnboarding();
					trainBoarding(this.currentStation);
				}
			}
		}
		currentCanton.exit();
	}

	
	/***
	 * How to deal with the situation of a passenger arriving to his destination
	 * and return the list of passenger who are leave the train
	 */
	
	public void trainUnboarding() {
		int index;
		for(index = 0; index<this.trainPassengers.size();index++) {
			if(this.trainPassengers.get(index).getIdArrival()==this.currentStation.getIdStation()){
				this.currentStation.getPassengers().add(this.trainPassengers.get(index));
				this.trainPassengers.remove(index);
			}
		}
	}
	
	/***
	 * How to deal with a passenger catching his train.
	 */
	public void trainBoarding(Station station) {
		if(this.maxPassenger == this.trainPassengers.size()) {
			//System.out.println("Train is full capacity :"+this.trainPassengers.size()+"\n");
		}else {
			while(this.maxPassenger != this.currentPassengers && !station.getPassengers().isEmpty()) {
				this.trainPassengers.add(station.getPassengers().get(0));
				station.getPassengers().remove(0);
				this.currentPassengers++;
			}
		}
	}
	
	public boolean hasEnteredStation (Train train, List<Station> stations){
		for(int index = 0; index<stations.size();index++) {
			if(train.position == stations.get(index).getPosition())
				train.setCurrentStation(stations.get(index));
				return true;
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return "Train [speed=" + speed + "]";
	}

	public void updatePosition() {
		position += speed;
	}

}