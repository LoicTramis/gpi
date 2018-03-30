package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.border.Border;
import core.Canton;
import core.Line;
import core.Station;
import core.Train;
import statistique.StatistiqueCore;
import statistique.Statistiques;

/**
 * @author Karim
 *
 */
public class SimulationGUI extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int TRAIN_BASIC_SPEED = 5;
	private static final int SIMULATION_DURATION = 10000;
	private static final int MAX_PASSENGER = 10000;
	private int currentTime = 0;
	private boolean triomagique=false;
	private SimulationDashboard dashboard; //JPanel
	private StatistiqueCore statistic;
	private Statistiques stat;
	private JPanel panelTab= new JPanel();
	private JPanel statJP = new JPanel();
	private JPanel panel3boutton= new JPanel();
	private HashMap<String , Integer> statHasmap;
	//private JTextField textFieldStation = new JTextField("", 20);
	//private JTextField textFieldTrain = new JTextField("", 20);
	private boolean stop ;
	
	//public static final int TIME_UNIT = 50;
	public static Dimension screenSize ;
	public static int TIME_UNIT = 50;

	public SimulationGUI() {
		super("EntroTrain");
		dashboard = new SimulationDashboard();
		statistic = new StatistiqueCore("EntroStats"); // je sais pas mtn
		stat = new Statistiques();
		statHasmap = stat.stationStat(dashboard.getArrayStation(dashboard.getStations()));
		statJP = statistic.barChart3D(statHasmap,"Gare","Gare","Satisfaction");
		
		
		setLayout(new BorderLayout());
		getContentPane().add(panelTab, BorderLayout.NORTH);
		getContentPane().add(panel3boutton, BorderLayout.SOUTH);
		getContentPane().add(statJP,BorderLayout.CENTER);
		/**
		 * @author Loic
		 */
		JButton animationButton = new JButton("Simulation");
		JButton statisticButton = new JButton("Statistiques");
		
		animationButton.setFocusable(false);
		animationButton.setAlignmentX(0);
		animationButton.setUI(new ButtonDesign());
		animationButton.setPreferredSize(new Dimension(120, 40));
		animationButton.setBorder(new RoundedBorder(10)); //10 is the radius
		animationButton.setBackground(Color.LIGHT_GRAY);
		animationButton.setForeground(Color.WHITE);
		// show the simulation
		animationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dashboard.setVisible(true);
				panel3boutton.setVisible(true);
				dashboard.repaint() ;    
				panel3boutton.repaint();
			}
		});
		
		statisticButton.setFocusable(false);
		statisticButton.setUI(new ButtonDesign());
		statisticButton.setPreferredSize(new Dimension(120, 40));
		statisticButton.setBorder(new RoundedBorder(10)); //10 is the radius
		statisticButton.setBackground(Color.LIGHT_GRAY);
		statisticButton.setForeground(Color.WHITE);
		// show the statistics
		statisticButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dashboard.setVisible(false);
				panel3boutton.setVisible(false);
			}
		});
		
		panelTab.add(animationButton);
		panelTab.add(statisticButton);
		/**
		 * @author Karim
		 *
		 */
		final JButton stopButton = new JButton("STOP");
		final JButton fastButton = new JButton("FAST");// Boutton faster
		final JButton slowButton = new JButton("SLOW");// Boutton slower
		
		stopButton.setFocusable(false);
		stopButton.setAlignmentX(0);
		stopButton.setUI(new ButtonDesign());
		stopButton.setPreferredSize(new Dimension(180, 60));
		stopButton.setBorder(new RoundedBorder(10)); //10 is the radius
		stopButton.setBackground(Color.RED);
		stopButton.setForeground(Color.WHITE);
		
		stopButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		stopButton.setFocusable(true);
	    		if(stop){
	    			stopButton.setText("STOP");
	    			stopButton.setBackground(Color.RED);
	    			stopButton.setForeground(Color.WHITE);
					System.out.println("------RESUMED------");
					//POUR QUE LE BOUTON MARCHE IL FAUT REMPLIR L4ARRAY LIST trains dans dashboard
					for(Train train : dashboard.getTrains())
						train.setSpeed(TRAIN_BASIC_SPEED);
					stop=false;
					
				}
				else{
					stopButton.setText("RESUME");
					stopButton.setBackground(Color.GREEN);
					stopButton.setForeground(Color.BLACK);
					System.out.println("------PAUSED------");
					for(Train train : dashboard.getTrains())
						train.setSpeed(0);
					stop=true;
				}
			}
		});



		
	    fastButton.setFocusable(false);
	    fastButton.setUI(new ButtonDesign());
	    fastButton.setBackground(Color.BLUE);
	    fastButton.setForeground(Color.WHITE);
	    fastButton.setPreferredSize(new Dimension(180, 60));
	    fastButton.setBorder(new RoundedBorder(10)); //10 is the radius
	    
	    fastButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				fastButton.setFocusable(true);
				if(TIME_UNIT > 5)
					TIME_UNIT = 10;
			}
	    });
	    	    
	    slowButton.setFocusable(false);
	    slowButton.setUI(new ButtonDesign());
	    slowButton.setBackground(Color.ORANGE);
	    slowButton.setForeground(Color.WHITE);
	    slowButton.setPreferredSize(new Dimension(180, 60));
	    slowButton.setBorder(new RoundedBorder(10)); //10 is the radius
	    
	    slowButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				slowButton.setFocusable(true);
				TIME_UNIT = 100;
			}
	    	 
	    });
	    
	    panel3boutton.add(stopButton);
	    panel3boutton.add(fastButton);
		panel3boutton.add(slowButton);
	 
	    screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    int length = (int)screenSize.getHeight();
	    int width = (int)screenSize.getWidth();
		
		JScrollPane scroll = new JScrollPane(dashboard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS) ;
	    scroll.setPreferredSize(new Dimension(width, length));
		getContentPane().add(scroll, BorderLayout.CENTER);
		

	    dashboard.setPreferredSize(new Dimension(1400, 720));
		dashboard.repaint() ;    
		panel3boutton.repaint();
		panelTab.repaint();
		
	    
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
		int counter=0;
		int trainchoice;
		String type;
		int i = 0;
		int speed;
		Canton startcanton;
		ArrayList<Station> stationsdeserved = new ArrayList<Station>();
		ArrayList<Integer> forks = new ArrayList<Integer>();
		Line line = dashboard.getLine();
		List<Station> stations = line.getStations();
		
		stop = false;
		while (currentTime <= SIMULATION_DURATION) {
			triomagique=false;
			
			if (currentTime % 12 == 0) {

				Canton firstCanton = line.getCantons().get(0);
				//ArrayList<Train> trains = GenerateStation.parseconfigfiletrain(line);
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
			counter++;
			if(counter%20==0) {
				triomagique=true;
				refill_stations(stations);
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

	public void refill_stations(List<Station> stations) {
		for(int i=0;i<stations.size();i++) {
			stations.get(i).generateMorePassengers(5*(stations.get(i).getPopularity()),stations.get(i).getIdStation(),16);
		}
		
	}
	
	public boolean getTriomagique() {
		return this.triomagique;
	}
	/**
	 * Put a border around the button
	 * 
	 * @author Loic Tramis
	 */
	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
}