package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import core.Canton;
import core.Line;
import core.LineBuilder;
import core.Passenger;
import core.Station;
import core.Train;

public class SimulationDashboard extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int START_X = 50;//65
	private static final int START_Y = 150;//300
	private BufferedImage stationImage = null;
	private BufferedImage trainImage = null;
	private BufferedImage trainWaitImage = null;
	private BufferedImage trainFailImage = null;
	private BufferedImage railImage = null;
	private BufferedImage railBrokeImage = null;

	private BufferedImage passengerUpImage = null;
	private BufferedImage passengerDownImage = null;
	private Line line;
	private List<Train> trains = new ArrayList<Train>();
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();

	ArrayList<Train> alltrains = new ArrayList<Train>();
	private List<Station> stations = new ArrayList<Station>();

	private LineBuilder lineBuilder;
	private int cpt=1;
	//private Station station1;

	public SimulationDashboard() {
		LineBuilder lineBuilder = new LineBuilder();
		lineBuilder.buildLine(3800, 100);
		//recupere les Station fu fichier ainsi que l'enssemble des cantons
		line = lineBuilder.getBuiltLine();
		for(int i=0; i<line.getStations().size();i++) {
			passengers = line.getStations().get(i).generatePassengers(50, line.getStations().get(i).getIdStation(), 16);
			line.getStations().get(i).setPassengers(passengers);
		}

		
		try {
			getImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addTrain(Train train) {
		trains.add(train);
	}
	
	public void addStation(Station station){
		stations.add(station);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		printLine(g2);
		printTrains(g2);
		
		//printStation(g2);
		//printPassengers(g2);
	}
	
	
	/**
	 * @author Karim
	 *
	 */
	private void printLine(Graphics2D g2) /*throws IOException*/ {
		Color L = Color.decode("#7284B0");
		g2.setColor(L);
		g2.setStroke(new BasicStroke(8));
		
		for (Canton canton : line.getCantons()) {
			g2.setColor(L);
			//position du railTransition bifuc au canton 9 id=100
			/*if(canton.getId()==100){
				g2.drawImage(transitionRailImage, START_X + canton.getStartPoint()-5, START_Y+32, 30,80, null);
			}
			//position du railTransition bifuc au canton 11 id=200
			if(canton.getId()==200){
				g2.drawImage(transitionRailImage, START_X + canton.getStartPoint()-5, START_Y+172-30, 30,80, null);
			}*/
			
			g2.setColor(Color.magenta);
			g2.setStroke(new BasicStroke(3));
			int startPoint = canton.getStartPoint();
			/*if((canton.getId()<200) && (canton.getId()>=100)){
				g2.drawImage(railImage, START_X + startPoint, START_Y+110, 128,32, null);
				//1er hauteur 2eme longeur
				g2.drawLine(START_X + startPoint, START_Y+140, START_X + startPoint, START_Y+110);			
			}
			else if(canton.getId()>=200){
				g2.drawImage(railImage, START_X + startPoint, START_Y+223, 128,32, null);
				//1er et deuxieme longeur
				g2.drawLine(START_X + startPoint, START_Y+250, START_X + startPoint, START_Y+223);
			}
			else{
				g2.drawImage(railImage, START_X + startPoint, START_Y, 128,32, null);
				g2.drawLine(START_X + startPoint, START_Y, START_X + startPoint, START_Y+27);
			}*/
			if((canton.getId()<100) && (canton.getId()>=0)){
				g2.drawImage(railImage, START_X + startPoint, START_Y, 128,32, null);
				g2.drawLine(START_X + startPoint, START_Y, START_X + startPoint, START_Y+27);
			}
			for (Station station : line.getStations()) {
				g2.setFont(new Font("Dialog", Font.BOLD, 12));
				g2.setColor(Color.BLACK);
				/*if(cpt==1 && station.getCantonid()<200 && station.getCantonid()>=100){
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +5+100*station.getLinelevel());
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y +229-100*station.getLinelevel()-20, 32,32, null);
					cpt++;
				}
				else if(cpt==2 && station.getCantonid()<200 && station.getCantonid()>=100){
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +55+100*station.getLinelevel());
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y +229-100*station.getLinelevel()-20, 32,32, null);
					cpt--;
				}*/
				/*else if(cpt==1 && station.getCantonid()>=200){
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +23+100*station.getLinelevel()-1-1-1-1-1);
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y+441-100*station.getLinelevel()-20, 32,32, null);
					cpt++;
				}
				else if(cpt==2 && station.getCantonid()>=200){
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +470-100*station.getLinelevel()-1-1-1+1);
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y +441-100*station.getLinelevel()-20, 32,32, null);
					cpt--;
				}*/
				if(cpt==1 && station.getCantonid()<100 && station.getCantonid() >= 0){
					int number = 0;
					/**
					for (Passenger passenger : passengers) {
						if (passenger.getUnboard()) {
							number++;
							System.out.println("Un passager est descendu");
						}
					}
					**/
					// the station
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +45-100*station.getLinelevel());
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y +2-100*station.getLinelevel()-20, null);
					// the passengers board
					g2.drawImage(passengerUpImage, station.getPosition()+10, START_Y +50-100*station.getLinelevel(), null);
					g2.drawString(station.getPassengers().size() + " attendent un train.", station.getPosition()+25, START_Y +65-100*station.getLinelevel());
					// the passengers unboard
					g2.drawImage(passengerDownImage, station.getPosition()+10, START_Y +80-100*station.getLinelevel(), null);
					g2.drawString(station.getPassengersdescending().size() + " sont descendus.", station.getPosition()+25, START_Y +95-100*station.getLinelevel());
				}
				/*else if(cpt==2 && station.getCantonid()<100 && station.getCantonid()>=0){
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +45-100*station.getLinelevel());
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y +18-100*station.getLinelevel()-20, 32,32, null);
					cpt--;
				}*/
			}
		}
	}
	
	/**
	 * @author Loic
	 *
	 */
	private void printTrains(Graphics2D g2){
		
		try{
			int i=1;
			g2.setStroke(new BasicStroke(5));
			for (Train train : trains) {
				if((train.getCurrentCanton().getId()>=0)&&(train.getCurrentCanton().getId()<100)){
					i=i+1;
					// The train is on an incident
					if (train.isStop()) {
						g2.setFont(new Font("Dialog", Font.BOLD, 12));
						g2.setColor(Color.RED);
						g2.drawLine(START_X + train.getPosition(), START_Y, START_X + train.getPosition(), START_Y - 80);
						g2.drawString(" !! INCIDENT !!", START_X + train.getPosition()-30, START_Y - 130);
						g2.drawString("Train : "+i, START_X + train.getPosition()-30, START_Y -90);
						g2.drawString("Passengers: "+train.getCurrentPassagengers(), START_X + train.getPosition()-30, START_Y -110);
						g2.drawImage(trainFailImage, START_X + train.getPosition()-30, START_Y,40,30, null);
					// The train is waiting for the canton to be freed
					} else if (train.getState() == State.WAITING) {
						g2.setFont(new Font("Dialog", Font.BOLD, 12));
						g2.setColor(Color.ORANGE);
						g2.drawString(" .. En attente ..", START_X + train.getPosition()-30, START_Y - 50);
						g2.drawString("Train : "+i, START_X + train.getPosition()-30, START_Y -10);
						g2.drawString("Passengers: "+train.getCurrentPassagengers(), START_X + train.getPosition()-30, START_Y -30);
						g2.drawImage(trainWaitImage, START_X + train.getPosition()-30, START_Y,40,30, null);
					// The train is running like a bird (wait...)
					} else {
						g2.setFont(new Font("Dialog", Font.BOLD, 12));
						g2.setColor(Color.BLUE);
						g2.drawString(" En avant toute :)", START_X + train.getPosition()-30, START_Y - 50);
						g2.drawString("Train : "+i, START_X + train.getPosition()-30, START_Y -10);
						g2.drawString("Passengers: "+train.getCurrentPassagengers(), START_X + train.getPosition()-30, START_Y -30);
						g2.drawImage(trainImage, START_X + train.getPosition()-30, START_Y,40,30, null);
					}
				}
			}
		}catch (ConcurrentModificationException e) {
			System.out.println("");
		} 
	}

	private void getImages() throws IOException {
		stationImage = ImageIO.read(new File("./img/entrotrain_station.png"));
		
		trainImage = ImageIO.read(new File("./img/entrotrain_train.png"));
		trainWaitImage = ImageIO.read(new File("./img/entrotrain_train_wait.png"));
		trainFailImage = ImageIO.read(new File("./img/entrotrain_train_fail.png"));
		
		passengerUpImage = ImageIO.read(new File("./img/entrotrain_passenger_up.png"));
		passengerDownImage = ImageIO.read(new File("./img/entrotrain_passenger_done.png"));

		railImage = ImageIO.read(new File("./img/rail.png"));
		railBrokeImage = ImageIO.read(new File("./img/rail_broke.png"));
	}
	
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}
	public List<Station> getStations() {
		return stations;
	}
	
	public ArrayList<Station> getArrayStation(List<Station> stations){
		ArrayList<Station> stationsArray = new ArrayList<Station>();
	    Iterator<Station> itrTemp = stations.iterator();
	    while(itrTemp.hasNext()){
	        Station stationTemp = itrTemp.next();
	        stationsArray.add(stationTemp);   
	    }
	    return stationsArray;	
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
}