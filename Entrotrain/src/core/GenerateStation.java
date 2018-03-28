/**
 * 
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import core.Canton;
import core.Line;
import core.Station;
import core.Train;
import exception.TerminusException;

/**
 * @author Mohamed II BAYO
 *
 */

public class GenerateStation {

	public static ArrayList<Station> configfilestation(Line stationline){
		ArrayList<Station> stations = new ArrayList<Station>();
		String line;
		String[] linesplitted;
		String[] linesplitted2;
		File configfile;
		int id=0;
		int linelevel;
		String stationid;
		int position;
		Station station;
		try{
			configfile = new File("./Entrotrain/src/fichier/ListStation.txt");
		}catch( NullPointerException e){
			configfile = new File("/home/bayo/git/gpi/Entrotrain/src/fichier/ListStation.txt");
		}
		
		try {
			BufferedReader configfilereader = new BufferedReader(new FileReader(configfile));
			try {
				while((line=configfilereader.readLine()) != null){
					linesplitted = line.split(":");
					linesplitted2 = linesplitted[0].split("=");
					stationid = linesplitted2[0];
					try {
						position = stationline.getCantonById(Integer.parseInt(linesplitted2[1])).getStartPoint()-5;
						linelevel = Integer.parseInt(linesplitted[1].substring(4));
						
						station = new Station(stationid,position, linelevel, id);
						station.setPopularity(2);//A AMELIORER AVEC LE FICHIER CONFIG
						station.setCantonid(stationline.getCantonById(Integer.parseInt(linesplitted2[1])).getId());
						stations.add(station);
						id++;
					} catch (TerminusException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stations;
	}
	
	
}



