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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import core.Canton;
import core.Line;
import core.LineBuilder;
import core.Passenger;
import core.Station;
import core.Train;

/**
 * @author tliu@u-cergy.fr
 */
public class SimulationDashboard extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int START_X = 50;//65
	private static final int START_Y = 150;//300
	private BufferedImage stationImage = null;
	private BufferedImage trainImage = null;
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
		lineBuilder.buildLine(800, 100);
		//recupere les Station fu fichier ainsi que l'enssemble des cantons
		line = lineBuilder.getBuiltLine();
		passengers = line.getStations().get(0).generatePassengers(50, 1, 2);
		line.getStations().get(0).setPassengers(passengers);
		
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
		printStation(g2);
		printPassengers(g2);
	}

	private void printLine(Graphics2D g2) {
		g2.setColor(Color.DARK_GRAY);
		g2.setStroke(new BasicStroke(1));
		g2.drawLine(START_X + 30, START_Y, START_X + line.getTotalLenght(), START_Y);
		
		for (Canton canton : line.getCantons()) {
			int startPoint = canton.getStartPoint() + 30;
			g2.setFont(new Font("Dialog", Font.PLAIN, 15));
//			g2.drawString("Canton " + canton.getId(), startPoint + 40, START_Y + 30);
//			g2.drawLine(START_X + startPoint, START_Y - 10, START_X + startPoint, START_Y + 10);
			int endPoint = canton.getEndPoint();
			g2.drawLine(START_X + endPoint, START_Y - 10, START_X + endPoint, START_Y + 10);
		}
	}

	private void printTrains(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(6));
		
		for (Train train : trains) {
			g2.setFont(new Font("Dialog", Font.PLAIN, 20));
//			g2.drawString("Train", START_X + train.getPosition(), START_Y - 35);
//			g2.drawString("Passengers : " + Integer.toString(train.getCurrentPassengers()), START_X + train.getPosition(), START_Y - 15);
			g2.drawImage(trainImage, START_X + train.getPosition() - 10, START_Y - 5, null);
		}
	}

	private void printStation(Graphics2D g2) {

		for(Station station : line.getStations()) {
			g2.setFont(new Font("Dialog", Font.BOLD, 12));
//			g2.drawString("" + station.getIdStation(), station.getPosition()+10, START_Y +45);
//			g2.drawString("Passengers : " + Integer.toString(station.getPassenger()), station.getPosition()+10, START_Y + 65);
//			g2.drawString("Popularity : " + Integer.toString(station.getPopularity()), station.getPosition()+10, START_Y + 85);

			g2.drawImage(stationImage, START_X + station.getPosition(), START_Y - 30, null);
			
		}
	}
	private void printPassengers(Graphics2D g2) {
		int passengerStartX = START_X;
		int passengerStartY = START_Y + 20;
		
		for (int index = 0; index < passengers.size(); index++) {
			if (index % 5 == 0) {
				passengerStartX = START_X;
				passengerStartY += 20;
			}
			passengerStartX += 10;
			g2.drawImage(passengerImage, passengerStartX, passengerStartY, null);
		}
	}
	
	private void getImages() throws IOException {
		stationImage = ImageIO.read(new File("./img/entrotrain_station.png"));
		trainImage = ImageIO.read(new File("./img/entrotrain_train.png"));
		passengerImage = ImageIO.read(new File("./img/entrotrain_passenger.png"));
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