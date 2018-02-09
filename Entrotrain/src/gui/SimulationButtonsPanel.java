package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SimulationButtonsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton buttonAddStation;
	private JButton buttonRemoveStation;
	private JButton buttonAddTrain;
	private JButton buttonRemoveTrain;

	private JButton buttonStart;
	private JButton buttonSlowSpeed;
	private JButton buttonFastSpeed;
	private JButton buttonStop;
	private JButton buttonClose;
	
	public SimulationButtonsPanel() {
		// Set the panel 
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new FlowLayout(5));
		
		// Set the addStation button
		buttonAddStation = new JButton("Add a station");
		buttonAddStation.setBackground(Color.BLUE);
		buttonAddStation.setForeground(Color.WHITE);
		buttonAddStation.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonAddStation.setUI(new ButtonDesign());
		
		// Set the removeStation button
		buttonRemoveStation = new JButton("Remove a station");
		buttonRemoveStation.setBackground(Color.ORANGE);
		buttonRemoveStation.setForeground(Color.WHITE);
		buttonRemoveStation.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonRemoveStation.setUI(new ButtonDesign());
		
		// Set the addTrain button
		buttonAddTrain = new JButton("Add a train");
		buttonAddTrain.setBackground(Color.BLUE);
		buttonAddTrain.setForeground(Color.WHITE);
		buttonAddTrain.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonAddTrain.setUI(new ButtonDesign());
		
		// Set the removeTrain button
		buttonRemoveTrain = new JButton("Remove a train");
		buttonRemoveTrain.setBackground(Color.ORANGE);
		buttonRemoveTrain.setForeground(Color.WHITE);
		buttonRemoveTrain.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonRemoveTrain.setUI(new ButtonDesign());
		
		// Set the start button
		buttonStart = new JButton("Start");
		buttonStart.setBackground(Color.GREEN);
		buttonStart.setForeground(Color.WHITE);
		buttonStart.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonStart.setUI(new ButtonDesign());
		
		// Set the stop button
		buttonSlowSpeed = new JButton("Slower");
		buttonSlowSpeed.setBackground(Color.GREEN);
		buttonSlowSpeed.setForeground(Color.WHITE);
		buttonSlowSpeed.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonSlowSpeed.setUI(new ButtonDesign());
		
		// Set the fastSpeed button
		buttonFastSpeed = new JButton("Faster");
		buttonFastSpeed.setBackground(Color.GREEN);
		buttonFastSpeed.setForeground(Color.WHITE);
		buttonFastSpeed.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonFastSpeed.setUI(new ButtonDesign());
		
		// Set the stop button
		buttonStop = new JButton("Stop");
		buttonStop.setBackground(Color.GREEN);
		buttonStop.setForeground(Color.WHITE);
		buttonStop.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonStop.setUI(new ButtonDesign());
		
		// Set the close button
		buttonClose = new JButton("Fermer");
		buttonClose.setBackground(Color.RED);
		buttonClose.setForeground(Color.WHITE);
		buttonClose.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonClose.setUI(new ButtonDesign());
		
		// Add event to the buttons
		
		
		
		// Add the button the to panel
		add(buttonStop);
		add(buttonClose);
	}
}
