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
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
	private BufferedImage railImage = null;
	private BufferedImage transitionRailImage = null;
	
	private BufferedImage passengerImage = null;
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
			passengers = line.getStations().get(i).generatePassengers(50, line.getStations().get(i).getIdStation(), 33);
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
				if(cpt==1 && station.getCantonid()<100 && station.getCantonid()>=0){
					g2.drawString("" + station.getId(), station.getPosition()+25, START_Y +45-100*station.getLinelevel());
					g2.drawImage(stationImage, START_X + station.getPosition()-10, START_Y +18-100*station.getLinelevel()-20, 32,32, null);
					g2.drawString("Passenger station = " + station.getPassengers().size(), station.getPosition()+25, START_Y +70-100*station.getLinelevel());
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
	 * @author Karim
	 *
	 */
	private void printTrains(Graphics2D g2){
		
		try{
			g2.setColor(Color.MAGENTA);
			int i=1;
			g2.setStroke(new BasicStroke(5));
			for (Train train : trains) {
				if((train.getCurrentCanton().getId()>=0)&&(train.getCurrentCanton().getId()<100)){
					g2.setFont(new Font("Dialog", Font.PLAIN, 10));
					g2.drawString("Train : "+i, START_X + train.getPosition()-30, START_Y -10);
					//if(train.getTriomagique1()==true) {
						g2.drawString("Passengers = "+train.getCurrentPassagengers(), START_X + train.getPosition()-30, START_Y -30);
					//}
					i=i+1;
					//40=largeur 30=taille y=position sur la rail
					g2.drawImage(trainImage, START_X + train.getPosition()-30, START_Y, 40,30, null);
				}
			}
		}catch (ConcurrentModificationException e) {
			System.out.println("");
		} 
	}

	/*private void printPassengers(Graphics2D g2) {
		int passengerStartX = START_X;
		int passengerStartY = START_Y +20;
		for (int index = 0; index < passengers.size(); index++) {
			if (index % 5 == 0) {
				passengerStartX += 150;
				passengerStartY = START_Y + 50;
			}
			passengerStartX += 10;
			g2.drawImage(passengerImage, passengerStartX, passengerStartY, null);
		}
	}*/
	private void getImages() throws IOException {
		stationImage = ImageIO.read(new File("./img/entrotrain_station.png"));
		trainImage = ImageIO.read(new File("./img/entrotrain_train.png"));
		passengerImage = ImageIO.read(new File("./img/entrotrain_passenger.png"));
		railImage = ImageIO.read(new File("./img/rail.png"));
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
}