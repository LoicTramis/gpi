package core;

import java.util.ArrayList;
import java.util.Random;

public class Passenger {
	private int age;
	private String sex;
	private int idArrival; 
	private int idDeparture;
	private int satisfaction;


	public Passenger(int age, String sex, int idArrival, int idDeparture, int satisfaction){
		this.age = age;
		this.sex = sex;
		this.idArrival = idArrival;
		this.idDeparture = idDeparture;
		this.satisfaction = satisfaction;
	}	
	public ArrayList<Passenger> generatePassengers(int numberPassenger,int idDeparture, int idTerminus) {
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		for(int i=0;i<=numberPassenger;i++) {
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
	
	
	public int getIdArrival() {
		return idArrival;
	}
	public void setIdArrival(int idArrival) {
		this.idArrival = idArrival;
	}
	public int getIdDeparture() {
		return idDeparture;
	}
	public void setIdDeparture(int idDeparture) {
		this.idDeparture = idDeparture;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public Passenger update_satisfaction(Passenger pass,int value) {
		pass.setSatisfaction(pass.getSatisfaction()-value);
		return pass;
	}
	public int getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}
	
	
	

}

