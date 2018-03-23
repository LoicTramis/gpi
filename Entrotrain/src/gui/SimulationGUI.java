package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import core.Canton;
import core.Line;
import core.Station;
import core.Train;

/**
 * @author tliu@u-cergy.fr
 */
public class SimulationGUI extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int TRAIN_SPEED_VARIATION = 3;
	private static final int TRAIN_BASIC_SPEED = 5;
	private static final int SIMULATION_DURATION = 10000;
	private static final int MAX_PASSENGER = 5;
	private int currentTime = 0;
	private SimulationDashboard dashboard; //JPanel
	private SimulationButtonsPanel buttonPanel = new SimulationButtonsPanel();
	private boolean stop ;
	private SimulationGUI instance ;
	
	//public static final int TIME_UNIT = 50;
	public static int TIME_UNIT = 50;

	public SimulationGUI() {
		super("EntroTrain");
		dashboard = new SimulationDashboard();
		
		setLayout(new BorderLayout());
		getContentPane().add(dashboard, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		//buttonPanel.getButtonStart().addActionListener(new StartPauseAction());
		
		/**
		 * @author Karim
		 *
		 */
		
		final JButton button = new JButton("STOP");
		button.setFocusable(false);
		ActionListener machin = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	    		button.setFocusable(true);
	    		if(stop){
					System.out.println("        RESUMED       ");
				button.setText("STOP");
				//POUR QUE LE BOUTON MARCHE IL FAUT REMPLIR L4ARRAY LIST trains dans dashboard
				for(Train train : dashboard.getTrains())
					train.restart();
				stop=false;
				}
				else{
					button.setText("RESUME");
					System.out.println("       PAUSED       ");
					for(Train train : dashboard.getTrains())
						train.setStop(true);
					stop=true;
				}
			}
		};
		button.setAlignmentX(0);
		button.addActionListener(machin);
		
		/**
		 * @author Karim
		 *
		 */
		//Boutton faster
	    final JButton speedIncrease=new JButton("FASTER");
	    speedIncrease.setFocusable(false);
	    speedIncrease.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				speedIncrease.setFocusable(true);
				if(TIME_UNIT > 5)
					TIME_UNIT-=5;
			}
	    });
	    
		/**
		 * @author Karim
		 *
		 */
	    //Boutton slower
	    final JButton speedDecrease=new JButton("SLOWER");
	    speedDecrease.setFocusable(false);
	    speedDecrease.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				speedDecrease.setFocusable(true);
				TIME_UNIT+=50;
			}
	    	
	    });
	    
	    dashboard.add(button);
		dashboard.add(speedIncrease);
		dashboard.add(speedDecrease);
		dashboard.repaint() ;    
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 700);
		setVisible(true);
		
	}
	
	//Pour lancer la simulation aller dans InitialGUI
	/*public static void main(String[] args) {
		SimulationGUI simulationGUI = new SimulationGUI();
		Thread simulationThread = new Thread(simulationGUI);
		simulationThread.start();
	}*/
	public static void main(String[] args) {
		SimulationGUI simulationGUI = new SimulationGUI();
		Thread simulationThread = new Thread(simulationGUI);
		simulationThread.start();
	}
	public void run() {
		int trainBasicSpeed = TRAIN_BASIC_SPEED;
		int index = 0;
		stop = false;
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
			if(!stop)
				currentTime++;
		}
	}

	/*public void closeAction(ActionEvent closeEvent, JFrame frame) {
		System.out.println("Simulation closed");
	}
	
	/*private class StartPauseAction implements ActionListener {
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
	}*/
}