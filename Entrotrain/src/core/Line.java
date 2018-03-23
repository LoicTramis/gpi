package core;

import java.util.ArrayList;
import java.util.List;

import core.Station;
import core.Train;
import core.Canton;
import exception.TerminusException;

/**
 * @author tliu@u-cergy.fr
 */
public class Line {
	private int totalLenght;
	private int usedLength = 0;

	private List<Canton> cantons = new ArrayList<Canton>();
	private List<Station> stations = new ArrayList<Station>();
	/**/private List<Train> trains = new ArrayList<Train>();

	public Line(int totalLenght) {
		this.totalLenght = totalLenght;
	}

	/*public void addCanton(int id, int cantonLength) {
		Canton canton;
		if (usedLength + cantonLength <= totalLenght) {
			canton = new Canton(id, cantonLength, usedLength);
			usedLength += cantonLength;
		} else {
			canton = new Canton(id, totalLenght - usedLength, usedLength);
			usedLength = totalLenght;
		}
		cantons.add(canton);
	}*/
	
	/**
	 * @author Karim
	 *
	 */
	
	public void addCanton(int id, int cantonLength) {
		Canton canton = null;
		if (usedLength + cantonLength <= totalLenght) {
			if((id<200)&&(id>100)){
				try {
					canton = new Canton(id, cantonLength, this.getCantonById(100).getStartPoint()+cantonLength*(id-100));
				} catch (TerminusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else if((id<300)&&(id>200)){
				try {
					canton = new Canton(id, cantonLength, this.getCantonById(200).getStartPoint()+cantonLength*(id-200));
				} catch (TerminusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else
				canton = new Canton(id, cantonLength, usedLength);
			usedLength += cantonLength;
		} else {
			if((id<200)&&(id>100)){
				try {
					canton = new Canton(id, cantonLength, this.getCantonById(100).getStartPoint()+cantonLength*(id-100));
				} catch (TerminusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else if((id<300)&&(id>200)){
				try {
					canton = new Canton(id, cantonLength, this.getCantonById(200).getStartPoint()+cantonLength*(id-200));
				} catch (TerminusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				canton = new Canton(id, totalLenght - usedLength, usedLength);
			usedLength = totalLenght;
		}
		cantons.add(canton);
	}
	
	/**
	 * @author Karim
	 *
	 */
	public void addCanton(int id, int cantonLength, int fork) {
		Canton canton = null;
		if (usedLength + cantonLength <= totalLenght) {
			try {
				canton = new Canton(id, cantonLength, this.getCantonById(fork).getStartPoint(), fork);
			} catch (TerminusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usedLength += cantonLength;
		} else {
			try {
				canton = new Canton(id, totalLenght - usedLength, this.getCantonById(fork).getStartPoint(), fork);
			} catch (TerminusException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usedLength = totalLenght;
		}
		cantons.add(canton);
	}

	public boolean isFull() {
		return usedLength == totalLenght;
	}

	public int getTotalLenght() {
		return totalLenght;
	}

	public int getUsedLength() {
		return usedLength;
	}

	public List<Canton> getCantons() {
		return cantons;
	}
	
	/**
	 * @author Karim
	 *
	 */

	public void addStation(String id, int stationPosition, int linelevel){
		Station station = new Station(id, stationPosition, linelevel);
		stations.add(station);
	}
	
	public List<Train> getTrains() {
		return trains;
	}
	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}
	
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public Canton getCantonByPosition(int position) throws TerminusException {
		for (Canton canton : cantons) {
			if (canton.getEndPoint() > position) {
				return canton;
			}
		}
		throw new TerminusException();
	}
	
	public Canton getCantonById(int id) throws exception.TerminusException {
		for (Canton canton : cantons) {
			while (canton.getId() == id) {
				return canton;
			}
		}
		throw new exception.TerminusException();
	}
	public List<Station> getStations() {
		return stations;
	}
	
	/**
	 * @author Karim
	 *
	 */
	public Canton searchCantonByName(String cantonName){
		Canton canton=null;
		for(Canton cantons : getCantons()){
			String name = "Canton ";
			name+=cantons.getId();
			if(name.equals(cantonName))
				canton=cantons;
		}
		return canton;
	}

}
