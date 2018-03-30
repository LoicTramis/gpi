package core;

import exception.TerminusException;
import gui.SimulationGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import core.Canton;
import core.Line;
import core.Station;


/**
 * @author cyril belmonte
 */
public class Train extends Thread {
	private volatile int position = 0;
	private boolean triomagique1=false;
	private Line line;
	private Canton currentCanton;
	private Station currentStation;
	private Incident incident = null;
	private int currentPassengers = 0;  //train leaves garage with 0 passengers
	private ArrayList<Passenger> trainPassengers = new ArrayList<Passenger>();
	private int maxPassenger;
	private int passengers = 0;
	
	/**
	 * @author Karim
	 *
	 */
	private String Id;
	private ArrayList<Station> stationsdeserved = new ArrayList<Station>();
	private ArrayList<Integer> forks = new ArrayList<Integer>();
	private boolean stop;
	//temps pour la simulation de s'executer 10nutié et 1 arret a une station
	private long exec10units=0;
	private int SLEEP_TIME = 2000;
	private long pause=0;

	/**
	 * Distance per time unit.
	 */
	private int speed;
	private int initialspeed;
	private boolean hasArrived = false;

	/**
	 * @author Karim
	 *
	 */
	public Train(Line line, Canton startCanton, int speed, ArrayList<Station> stationsdeserved, String type, ArrayList<Integer> forks, int o) {
		this.line = line;
		currentCanton = startCanton;
		currentCanton.enter(this);
		this.speed = speed;
		this.stationsdeserved.addAll(stationsdeserved);
		this.Id=type;
		this.forks.addAll(forks);
		this.initialspeed=speed;
		this.stop=false;
		LinkTrainStations();
	}
	
	/**
	 * @author Karim
	 *
	 */
	public Train(Line line, Canton startCanton, int speed, ArrayList<Station> stationsdeserved, String type, ArrayList<Integer> forks) {
		this.line = line;
		currentCanton = startCanton;
		this.speed = speed;
		this.stationsdeserved.addAll(stationsdeserved);
		this.Id=type;
		this.forks.addAll(forks);
		this.initialspeed=speed;
		this.stop=false;
	}
	
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
		this.stop = false;
		
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
	

	
	/***
	 * How to deal with the situation of a passenger arriving to his destination
	 * and return the list of passenger who are leave the train
	 */
	
	public void trainUnboarding() {
		int index;
		for(index = 0; index<this.trainPassengers.size();index++) {
			if(this.trainPassengers.get(index).getIdArrival()==this.currentStation.getIdStation()){
				this.currentStation.getPassengers().add(this.trainPassengers.get(index));
				this.currentStation.getPassengersdescending().add(this.trainPassengers.get(index));
				this.trainPassengers.get(index).setUnboard(true);
				//this.currentStation.getPassengers().get(this.currentStation.getPassengers().size()-1).setUnboard(true);
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
			if(train.position == stations.get(index).getPosition()){
				train.setCurrentStation(stations.get(index));
				return true;
			}
		}
		return false;
	}


	@Override
	public synchronized void run() {
		while (!hasArrived) {
			triomagique1=false;
			if(stop){
				try {
					//System.out.println("repartisNNNNNNN\n");
					wait();
					//System.out.println("repartis\n");
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.stop=false;
			}
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
				this.getCurrentCanton().spawnIncident(); //1/4 qu'un incident apparaisse
				if (this.getCurrentCanton().getIncident() != null){ 
					if (this.getCurrentCanton().getIncident().getIsSolved() == false){
						System.out.println("Train on canton " + this.getCurrentCanton().getId() + " was stopped by an incident");
						//this.setPosition(position - 1);
						this.getCurrentCanton().getIncident().stopTrain(this);
						this.getCurrentCanton().setIncident(null);
					}
				}
				updatePosition();
				if (hasEnteredStation(this,line.getStations())) {
					trainUnboarding();
					trainBoarding(this.currentStation);
					triomagique1=true;
				}
			}
		}
		currentCanton.exit();
	}
	

	
	
	/**
	 * @author Karim
	 *
	 */
	
	// retourne le temps à une station en milliesec
	public long TimeFromStation(Station station){
		return ((long)DistanceFromStation(station)/10)*this.exec10units
				+ this.getStationsdeserved().indexOf(station)*this.pause;
	}
	
	//Array des différents temps d'un train au station deservis 
	public ArrayList<Long> TimeFromStationsDeserved(){
		ArrayList<Long> time = new ArrayList<Long>();
		for(Station station : this.getStationsdeserved())
			time.add(TimeFromStation(station));
		return time;
	}
	
	//Array des Station deservis par un train
	public ArrayList<Station> getStationsdeserved() {
		return stationsdeserved;
	}

	public void setStationsdeserved(ArrayList<Station> stationsdeserved) {
		this.stationsdeserved = stationsdeserved;
	}

	//puisque stationdeserved est un Array des station deservis par le train
	//on parcoure toute ces station dans lequelles sera ajouté le train qui les a deservis
	public void LinkTrainStations(){
		for (Station station : stationsdeserved) {
			station.addTrainArriving(this);
		}
	}
	
	public String getType() {
		return Id;
	}

	public void setType(String type) {
		Id = type;
	}

	public ArrayList<Integer> getForks() {
		return forks;
	}

	public void setForks(ArrayList<Integer> forks) {
		this.forks = forks;
	}
	//calcule la distance entre la prochaine Station et le Train
	public int DistanceFromStation(Station station){
		return station.getPosition()-this.getPosition();
	}

	
	public ArrayList<Integer> DistanceFromStationsDeserved(){
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for(Station station : this.getStationsdeserved())
			distances.add(DistanceFromStation(station));
		return distances;
	}
	
	//pour manager le train à savoir si le train reste a quai ou ne fait pas d'arret
	//peut etre inutile
	private int forking(){
		int fork=0;
		if((this.currentCanton.getId()==8)&&(forks.get(0)==1))
			fork=1;
		if((this.currentCanton.getId()==110)&&(forks.get(1)==1))
			fork=2;
		return fork;
	}
	
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public synchronized void restart(){
		notify();
	}

	public long getExec10units() {
		return exec10units;
	}

	public void setExec10units(long exec10units) {
		this.exec10units = exec10units;
	}
	
	@Override
	public String toString() {
		return "Train [position=" + position + ", Type=" + Id + ", line=" + line + ", currentCanton=" + currentCanton
				+ ", forks=" + forks + ", speed=" + speed + ", hasArrived="
				+ hasArrived + "stationsdeserved["+ this.stationsdeserved.get(0).getId()+"]";
	}

	public void updatePosition() {
		position += speed;
	}
	
	public boolean getTriomagique1() {
		return this.triomagique1;
	}
	private boolean deserting() {
		for(Station station : stationsdeserved){
			//si le train a depasser ou et dans la station qui a une pposition fixe alors
			if((position+speed>=station.getPosition())){
				//si la station a dans son repertoire de train le train en question on le supprime
				if(station.getTrainsarriving().contains(this))
					station.getTrainsarriving().remove(this);
				//si l'Array des station deservis par le train detient la station on l'enleve de l'Array
				if(this.getStationsdeserved().contains(station))
					this.getStationsdeserved().remove(station);
				//si il est vide on le supprime de la line
				if(this.getStationsdeserved().isEmpty()){
					this.line.getTrains().remove(this);
				}
				
				return true;
			}
		}
		return false;
	}
	
	public int getCurrentPassagengers() {
		return trainPassengers.size();
	}

}