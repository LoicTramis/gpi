package core;


public class Passenger {
	private int age;
	private String sexe;
	private Station stationOfArrival; 
	private Station stationOfDeparture;
	private int satisfaction;


public Passenger(int age, String sexe, Station arrival, Station departure, int satisfaction){
	this.age = age;
	this.sexe = sexe;
	this.stationOfArrival = stationOfArrival;
	this.stationOfDeparture = stationOfDeparture;
	this.satisfaction = satisfaction;
}



	public Station getStationOfArrival() {
		return stationOfArrival;
	}
	public void setStationOfArrival(Station stationOfArrival) {
		this.stationOfArrival = stationOfArrival;
	}
	
	public Station getStationOfDeparture() {
		return stationOfDeparture;
	}
	public void setStationOfDeparture(Station stationOfDeparture) {
		this.stationOfDeparture = stationOfDeparture;
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

