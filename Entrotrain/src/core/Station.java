package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import core.Train;

/**@author Cyril Belmonte
 * @version 1.0
 * 
 */

public class Station {
	
	private int passenger;
	private int popularity;
	private int position;
	private int idStation;
	private ArrayList<Passenger> passengers =new ArrayList<Passenger>();
	private ArrayList<Passenger> passengersdescending =new ArrayList<Passenger>();
	
	public ArrayList<Passenger> getPassengersdescending() {
		return passengersdescending;
	}

	public void setPassengersdescending(ArrayList<Passenger> passengersdescending) {
		this.passengersdescending = passengersdescending;
	}

	/**
	 * @author Karim
	 *
	 */
	private String id;//nom
	private ArrayList<Train> trainsarriving = new ArrayList<Train>();
	private int linelevel;//niveau pour Gui
	private int cantonid;
	
	/**
	 * @author Karim
	 *
	 */
	public Station(String id, int position, int linelevel,int idStation){
		this.id=id;
		this.position=position;
		this.linelevel=linelevel;
		this.idStation=idStation;
	}
	
	public Station(int numberPassenger, int popularity, int idStation2, int position, ArrayList<Passenger> passengers) {
		this.passenger = numberPassenger;
		this.passengers = passengers;
		this.popularity = popularity;
		this.idStation = idStation2;
		this.position = position;
	}
	
	public void addTrainArriving(Train train){
		this.trainsarriving.add(train);
	}
	
	public int getPassenger() {
		return passenger;
	}

	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getIdStation() {
		return idStation;
	}

	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}
	
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public ArrayList<Passenger> generatePassengers(int numberPassenger,int idDeparture, int idTerminus) {
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		for(int i=1;i<=numberPassenger;i++) {
			Passenger pass = generatePassenger(idDeparture,idTerminus);
			passengers.add(pass);
		}
		return passengers;
	}	
	
	public void generateMorePassengers(int numberPassenger,int idDeparture, int idTerminus) {
		for(int i=1;i<=numberPassenger;i++) {
			Passenger pass = generatePassenger(idDeparture,idTerminus);
			this.getPassengers().add(pass);
		}
	}
	
	public Passenger generatePassenger(int idDeparture,int idTerminus){
		Random rand = new Random();
		int age = 7 + rand.nextInt(99-7);
		
		int idStationArrival = (idDeparture)+rand.nextInt(idTerminus-idDeparture+1);
//		System.out.println(idStationArrival);
		ArrayList<String> sexs = new ArrayList<String>();
		sexs.add("m");
		sexs.add("s");
		int index = rand.nextInt(1-0);
		String sex = sexs.get(index);
		Passenger pass = new Passenger(age,sex,idStationArrival,idDeparture, 5);
		return pass;
	}
	
	/**
	 * @author Karim
	 *
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public int getLinelevel() {
		return linelevel;
	}

	public void setLinelevel(int linelevel) {
		this.linelevel = linelevel;
	}
	
	//Array de Train avec tous les trains présents dans une gare
	public ArrayList<Train> getTrainsarriving() {
		return trainsarriving;
	}

	public void setTrainsarriving(ArrayList<Train> trainsarriving) {
		this.trainsarriving = trainsarriving;
	}
	
	//Array des distances trains/station
	public ArrayList<Integer> getTrainsArrivingDistance(){
		ArrayList<Integer> trainarrivingdistance = new ArrayList<Integer>();
		for(Train train : this.getTrainsarriving())
			trainarrivingdistance.add(train.DistanceFromStation(this));
		return trainarrivingdistance;
	}

	public int getCantonid() {
		return cantonid;
	}

	public void setCantonid(int cantonid) {
		this.cantonid = cantonid;
	}

}