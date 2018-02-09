package core;
/**
 * @author Cyril Belmonte
 * @version 1.0
 */

public class Incident {
	private int degree_of_incident;
	
	public Incident(int degree_of_incident) {
		this.degree_of_incident = degree_of_incident;
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
}