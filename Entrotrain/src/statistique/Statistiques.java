package statistique;

import java.util.ArrayList;
import java.util.HashMap;

import core.Station;

public class Statistiques {
	
	
	public HashMap<String, Integer> stationStat(ArrayList<Station> station) {
		int averageSatisfaction = 0 ;
		HashMap<String, Integer> satis = new HashMap();
		for(int index = 0 ; index <station.size() ; index++) {
			for(int index2=0 ; index2 < station.get(index).getPassengers().size(); index2++) {
				averageSatisfaction=averageSatisfaction+station.get(index).getPassengers().get(index2).getSatisfaction();
			}
			if(averageSatisfaction==0) {
				satis.put(station.get(index).getId(), 0);
			}else {
				satis.put(station.get(index).getId(), averageSatisfaction);
			}
		}
		return satis;
	}

}
