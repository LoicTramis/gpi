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

