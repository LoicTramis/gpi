package core;

import java.util.ArrayList;
import java.util.Random;

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
	
	/**
	 * @param numberPassenger
	 * @param popularity
	 * @param position
	 * @param id
	 * @param passenger
	 */
	public Station(int numberPassenger, int popularity, int idStation2, int position, ArrayList<Passenger> passengers) {
		this.passenger = numberPassenger;
		this.passengers = passengers;
		this.popularity = popularity;
		this.idStation = idStation2;
		this.position = position;	
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
	
	public Passenger generatePassenger(int idDeparture,int idTerminus){
		Random rand = new Random();
		int age = 7 + rand.nextInt(99-7);
		int idStationArrival = (idDeparture+1)+rand.nextInt(idTerminus-idDeparture+1);
		ArrayList<String> sexs = new ArrayList<String>();
		sexs.add("m");
		sexs.add("s");
		int index = rand.nextInt(1-0);
		String sex = sexs.get(index);
		Passenger pass = new Passenger(age,sex,idStationArrival,idDeparture, 5);
		return pass;
	}

}