package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import core.Canton;
import core.Line;
import core.Station;
import core.Train;

/**
 * @author Karim
 *
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
	private JPanel panel3boutton= new JPanel();
	private boolean stop ;
	private SimulationGUI instance ;
	
	//public static final int TIME_UNIT = 50;
	public static Dimension screenSize ;
	public static int TIME_UNIT = 50;

	public SimulationGUI() {
		super("EntroTrain");
		dashboard = new SimulationDashboard();
		setLayout(new BorderLayout());
		//getContentPane().add(dashboard, BorderLayout.CENTER);
		getContentPane().add(panel3boutton, BorderLayout.SOUTH);
		
		JButton button = new JButton("STOP");
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
	    JButton speedIncrease=new JButton("FASTER");
	    speedIncrease.setFocusable(false);
	    speedIncrease.addActionListener(new ActionListener(){
			@Override
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
	    JButton speedDecrease=new JButton("SLOWER");
	    speedDecrease.setFocusable(false);
	    speedDecrease.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				speedDecrease.setFocusable(true);
				TIME_UNIT+=50;
			}
	    	 
	    });
	    panel3boutton.add(button);
	    panel3boutton.add(speedIncrease);
		panel3boutton.add(speedDecrease);
	 
	    screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    int length = (int)screenSize.getHeight();
	    int width = (int)screenSize.getWidth();
		
		JScrollPane scroll = new JScrollPane(dashboard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS) ;
	    scroll.setPreferredSize(new Dimension(width, length));
		getContentPane().add(scroll, BorderLayout.CENTER);
		

	    dashboard.setPreferredSize(new Dimension(3700, 3000));
	    //dashboard.add(button);
		//dashboard.add(speedIncrease);
		//dashboard.add(speedDecrease);
		dashboard.repaint() ;    
		panel3boutton.repaint();
		
	    
		this.setTitle("EntroTrain");
	    this.setSize(width, length);
	    this.setLocationRelativeTo(null);
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	/**
	 * @author tliu@u-cergy.fr
	 */
	public synchronized void run() {
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
					//trainBasicSpeed += TRAIN_SPEED_VARIATION;
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
}