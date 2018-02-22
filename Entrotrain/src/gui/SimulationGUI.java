package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JFrame;

import core.Canton;
import core.Line;
import core.Station;
import core.Train;

/**
 * @author tliu@u-cergy.fr
 */
public class SimulationGUI extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int TRAIN_SPEED_VARIATION = 0;
	private static final int TRAIN_BASIC_SPEED = 2;
	private static final int SIMULATION_DURATION = 1000;
	private static final int MAX_PASSENGER = 200;
	private int currentTime = 0;
	private SimulationDashboard dashboard = new SimulationDashboard(); //JPanel
	private SimulationButtonsPanel buttonPanel = new SimulationButtonsPanel();
	
	public static final int TIME_UNIT = 50;

	public SimulationGUI() {
		super("EntroTrain");
		setLayout(new BorderLayout());
		getContentPane().add(dashboard, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 700);
		setVisible(true);
	}

	public static void main(String[] args) {
		SimulationGUI simulationGUI = new SimulationGUI();
		Thread simulationThread = new Thread(simulationGUI);
		simulationThread.start();
	}

	public void run() {
		int trainBasicSpeed = TRAIN_BASIC_SPEED;
		
		while (currentTime <= SIMULATION_DURATION) {
			
			if (currentTime % 12 == 0) {
				Line line = dashboard.getLine();
				Canton firstCanton = line.getCantons().get(0);
				List<Station> stations = line.getStations();
				int currentPassenger = 0;
				
				if (firstCanton.isFree()) {
					Train newTrain = new Train(line, firstCanton, null, trainBasicSpeed, currentPassenger, MAX_PASSENGER, null);
					if (stations.get(0).getPassenger() != 0) {
						newTrain.trainBoarding(stations.get(0));
						System.out.println("Passengers montent");
					}
					dashboard.addTrain(newTrain);
					newTrain.start();
					trainBasicSpeed += TRAIN_SPEED_VARIATION;
					System.out.println("New Train created " + newTrain.toString());
					System.out.println("Passager dans le train : " + newTrain.getCurrentPassengers());
					System.out.println("Passager dans la station " + line.getStations().get(0).getPassenger());
				}

			}
			dashboard.repaint();
			try {
				Thread.sleep(TIME_UNIT);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			currentTime++;
		}
	}

	public void closeAction(ActionEvent closeEvent, JFrame frame) {
		System.out.println("Simulation closed");
	}
}