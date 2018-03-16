package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private static final int TRAIN_BASIC_SPEED = 5;
	private static final int SIMULATION_DURATION = 1000;
	private static final int MAX_PASSENGER = 5;
	private int currentTime = 0;
	private SimulationDashboard dashboard = new SimulationDashboard(); //JPanel
	private SimulationButtonsPanel buttonPanel = new SimulationButtonsPanel();
	private boolean stop = true;
	private SimulationGUI instance = this;
	
	public static final int TIME_UNIT = 50;
	

	public SimulationGUI() {
		super("EntroTrain");
		setLayout(new BorderLayout());
		getContentPane().add(dashboard, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.getButtonStart().addActionListener(new StartPauseAction());
		
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
		int index = 0;
		while (currentTime <= SIMULATION_DURATION) {
			
			if (currentTime % 12 == 0) {
				Line line = dashboard.getLine();
				Canton firstCanton = line.getCantons().get(0);
				List<Station> stations = line.getStations();
				System.out.println("Passager : " + stations.get(0).getPassengers().size());
				int currentPassenger = 0;
				
				if (firstCanton.isFree()) {
					Train newTrain = new Train(line, firstCanton, stations.get(0), trainBasicSpeed, currentPassenger, MAX_PASSENGER);
					dashboard.addTrain(newTrain);
					newTrain.start();
					if (stations.get(0).getPassengers().size() != 0) {
						newTrain.trainBoarding(stations.get(0));
						System.out.println("Train :" + index);
						System.out.println("Passengers montent");
						System.out.println("Passager dans le train : " + newTrain.getTrainPassengers().size());
						index++;
					}
					trainBasicSpeed += TRAIN_SPEED_VARIATION;
					System.out.println("Passager dans le train : " + newTrain.getCurrentPassengers());
					System.out.println("Passager sur le quai " + line.getStations().get(0).getPassengers().size());
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
	
	private class StartPauseAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!stop) {
				stop = true;
				buttonPanel.getButtonStart().setText("Start");
				Thread trainThread = new Thread(instance);
				try {
					trainThread.wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				stop = false;
				buttonPanel.getButtonStart().setText("Pause");
				Thread trainThread = new Thread(instance);
				trainThread.start();
			}
		}
	}
}