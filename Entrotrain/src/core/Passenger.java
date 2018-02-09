package core;

import java.util.ArrayList;

public class Passenger {
	private int age;
	private String sex;
	private Station arrived_station;
	private Station departure_station;
	private int satisfaction;
	
	
	
	public Passenger(int age, String sex, Station arrived_station, Station departure_station, int satisfaction) {
		this.age = age;
		this.sex = sex;
		this.arrived_station = arrived_station;
		this.departure_station = departure_station;
		this.satisfaction = satisfaction;
	}
	public Passenger update_satisfaction(Passenger pass,int value) {
		pass.setSatisfaction(pass.getSatisfaction()-value);
		return pass;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Station getArrived_station() {
		return arrived_station;
	}
	public void setArrived_station(Station arrived_station) {
		this.arrived_station = arrived_station;
	}
	public Station getDeparture_station() {
		return departure_station;
	}
	public void setDeparture_station(Station departure_station) {
		this.departure_station = departure_station;
	}
	public int getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}
	
	
	

}
