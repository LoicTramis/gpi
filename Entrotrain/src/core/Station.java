package core;

import java.util.ArrayList;

/**@author Cyril Belmonte
 * @version 1.0
 * 
 */

public class Station {
	
	private int passenger;
	private int popularity;
	private int position;
	private int idStation;
	private ArrayList<Passenger> passengers;
	
	/**
	 * @param numberPassenger
	 * @param popularity
	 * @param position
	 * @param id
	 * @param passenger
	 */
	public Station(int numberPassenger, int popularity, int idStation, int position, ArrayList<Passenger> passengers) {
		this.passenger = numberPassenger;
		this.passengers = passengers;
		this.popularity = popularity;
		this.idStation = idStation;
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

	

}