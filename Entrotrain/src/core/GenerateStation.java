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
 * @author Antoine
 *
 */

public class GenerateStation {

	public static ArrayList<Station> configfilestation(Line stationline){
		ArrayList<Station> stations = new ArrayList<Station>();
		String line;
		String[] linesplitted;
		String[] linesplitted2;
		File configfile;
		
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
						
						station = new Station(stationid,position, linelevel);
						station.setCantonid(stationline.getCantonById(Integer.parseInt(linesplitted2[1])).getId());
						stations.add(station);
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
	
	public static ArrayList<Train> parseconfigfiletrain(Line trainline){
		File configfile;
		try{
			configfile = new File("./Entrotrain/src/fichier/ListTrains.txt");
		}catch( NullPointerException e){
			configfile = new File("/home/bayo/git/gpi/Entrotrain/src/fichier/ListTrains.txt");
		}
		String line="1:2";
		String[] linesplitted = null;
		String[] linesplitted2 = null;
		ArrayList<Train> trains = new ArrayList<Train>();
		
		
		Train train ;
		int trainstationdeservedid;
		ArrayList<Station> trainstationsdeserved;
		String traintype;
		int trainstartCantonId;
		Canton trainstartCanton;
		int trainspeed;
		ArrayList<Integer> forks;
		int fork;
		
		int j=3;
		
		
		try {
			BufferedReader configfilereader = new BufferedReader(new FileReader(configfile));
			try {
				while((line=configfilereader.readLine()) != null){

						linesplitted = line.split(":");
						
						
						traintype = linesplitted[0].substring(5);
						
						trainstartCantonId = Integer.parseInt(linesplitted[1].substring(12)); 
						trainstartCanton = trainline.getCantons().get(trainstartCantonId-1);
						
						trainspeed = Integer.parseInt(linesplitted[2].substring(6));
						
						trainstationsdeserved = new ArrayList<Station>();
						linesplitted2 = linesplitted[3].split(",");
						for(int i=0; i<linesplitted2.length; i++){
							if(i==0){
								trainstationdeservedid = Integer.parseInt(linesplitted2[i].substring(5));
								trainstationsdeserved.add(trainline.getStations().get(trainstationdeservedid-1));
							}else{
								trainstationdeservedid = Integer.parseInt(linesplitted2[i]);
								trainstationsdeserved.add(trainline.getStations().get(trainstationdeservedid-1));
							}
							
						}
						
						forks = new ArrayList<Integer>();
						linesplitted2 = linesplitted[4].split(",");
						for(int i=0; i<linesplitted2.length ; i++){
							if(i==0){
								fork = Integer.parseInt(linesplitted2[i].substring(6,7));
								forks.add(fork);
							}else{
								fork = Integer.parseInt(linesplitted2[i]);
								forks.add(fork);
							}
						}
						train = new Train(trainline, trainstartCanton,trainspeed,trainstationsdeserved,traintype, forks, 1);
						trains.add(train);
					
					
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
		return trains;
		
	}
}



