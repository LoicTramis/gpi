package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

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
	private static final int START_X = 20;
	private static final int START_Y = 150;
	private Line line;
	private List<Train> trains = new ArrayList<Train>();
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private Station station1;

	public SimulationDashboard() {
		LineBuilder lineBuilder = new LineBuilder();
		lineBuilder.buildLine(900, 300);
		line = lineBuilder.getBuiltLine();
		
		station1 = new Station(200, 5, 1, START_X, passengers);
	}

	public void addTrain(Train train) {
		trains.add(train);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		printLine(g2);
		printTrains(g2);
		printStation(g2);
	}

	private void printLine(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(START_X, START_Y, START_X + line.getTotalLenght(), START_Y);
		
		for (Canton canton : line.getCantons()) {
			int startPoint = canton.getStartPoint();
			g2.setFont(new Font("Dialog", Font.PLAIN, 25));
			g2.drawString("Canton " + canton.getId(), startPoint + 100, START_Y + 30);
			g2.drawLine(START_X + startPoint, START_Y - 10, START_X + startPoint, START_Y + 10);
			int endPoint = canton.getEndPoint();
			g2.drawLine(START_X + endPoint, START_Y - 10, START_X + endPoint, START_Y + 10);
		}
	}

	private void printTrains(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(6));
		
		for (Train train : trains) {
			g2.setFont(new Font("Dialog", Font.PLAIN, 20));
			g2.drawString("Train", START_X + train.getPosition(), START_Y - 25);
			g2.drawLine(START_X + train.getPosition(), START_Y - 5, START_X + train.getPosition(), START_Y + 5);
		}
	}

	private void printStation(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(10));
			g2.setFont(new Font("Dialog", Font.PLAIN, 20));
			g2.drawString("Station", START_X, START_Y);
			g2.drawLine(START_X - 5, START_Y - 5, START_X + 5, START_Y + 5);
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

	public Station getStation() {
		return station1;
	}
}
