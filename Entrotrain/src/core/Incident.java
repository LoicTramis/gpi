package core;
/**
 * @author Cyril Belmonte
 * @version 1.0
 */

public class Incident extends Thread {
	private int degree_of_incident;
	private int time_of_solving;
	private boolean isSolved = false;
	
	public Incident(int degree_of_incident, int time_of_solving) {
		this.degree_of_incident = degree_of_incident;
		this.time_of_solving = time_of_solving;
	}
	
	
	
	public synchronized void stopTrain(Train train) {
		if(isSolved != true){
			try {
				train.setPosition(train.getPosition() - 1);
				wait(time_of_solving); //attendre 5 sec
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			trainNotify();	
		}
	}




	public synchronized void trainNotify(){
		this.setIsSolved(true);
		notify();
	}
	/**
	 * This method is to generate the time of the incident
	 * 
	 * @param degree_of_incident
	 * @return time
	 */
	public int getIncidentTime(int degree_of_incident) {
		int time ; 
		if(degree_of_incident <2) {
			time = 2;
		}else if (degree_of_incident < 4){
			time = 3;
		}else if (degree_of_incident < 6) {
			time = 4;
		}else {
			time = 0;
		} 
		return time;
	}
	
	/**this fonction is to generat the note to regulate the
	 * globale note 
	 * 
	 * @param time
	 * @return note
	 */
	public int getIncidentSatisfaction(int time) {
		int note;
		if(time <2) {
			note = 2;
		}else if (time < 4){
			note = 4;
		}else if (time < 6) {
			note = 5;
		}else {
			note = 0;
		}
		return note;
	}




	public boolean getIsSolved(){
		return this.isSolved;
	}
	public void setIsSolved(boolean bool){
		this.isSolved = bool;
	}
	
	public int getTime_of_solving() {
		return time_of_solving;
	}



	public void setTime_of_solving(int time_of_solving) {
		this.time_of_solving = time_of_solving;
	}
}


