package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.Line;
import core.Passenger;
import core.Station;
import exception.TerminusException;

/**
 * @author BAYO Mohamed II
 */

public class GenerateStation {

	public static ArrayList<Station> configfilestation(Line stationline){
		ArrayList<Station> stations = new ArrayList<Station>();
		String line;
		String[] linesplitted;
		File configfile;
		
		 int numberPassenger = 0;
		 int popularity = 0;
		 int position;
		 String idStation;
		 ArrayList<Passenger> passengers = null;
		Station station;
		
		try{
			configfile = new File("./src/fichier/ListStation.txt");
		}catch( NullPointerException e){
			configfile = new File("./src/fichier/ListStation.txt");
		}
		
		try {
			BufferedReader filereader = new BufferedReader(new FileReader(configfile));
			try {
				while((line = filereader.readLine()) != null){
					linesplitted = line.split("=");
					idStation = linesplitted[0];
					System.out.println(idStation);	
					System.out.println(linesplitted[1]);
					try {
						position = stationline.getCantonById(Integer.parseInt(linesplitted[1])).getStartPoint()-5;		
						station = new Station(numberPassenger,popularity,idStation,position,passengers);
						//station.setCantonid(stationline.getCantonById(Integer.parseInt(linesplitted[1])).getId());
						stations.add(station);
						
					} catch ( TerminusException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				filereader.close();
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