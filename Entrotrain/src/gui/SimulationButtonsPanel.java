package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Panel that contains all the buttons
 * 
 * @author Lo&iuml;c Tramis
 * @version 1.0
 *
 */
public class SimulationButtonsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton buttonAddStation;
	private JButton buttonRemoveStation;
	private JButton buttonAddTrain;
	private JButton buttonRemoveTrain;

	private JButton buttonStart;
	private JButton buttonPause;
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
		
		// Set the start button
		buttonPause = new JButton("Pause");
		buttonPause.setBackground(Color.GREEN);
		buttonPause.setForeground(Color.WHITE);
		buttonPause.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonPause.setUI(new ButtonDesign());
		
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
		
		// Add the button the to panel
//		add(buttonStart);
//		add(buttonPause);
//		add(buttonStop);
//		add(buttonClose);
	}

	public JButton getButtonAddStation() {
		return buttonAddStation;
	}

	public void setButtonAddStation(JButton buttonAddStation) {
		this.buttonAddStation = buttonAddStation;
	}

	public JButton getButtonRemoveStation() {
		return buttonRemoveStation;
	}

	public void setButtonRemoveStation(JButton buttonRemoveStation) {
		this.buttonRemoveStation = buttonRemoveStation;
	}

	public JButton getButtonAddTrain() {
		return buttonAddTrain;
	}

	public void setButtonAddTrain(JButton buttonAddTrain) {
		this.buttonAddTrain = buttonAddTrain;
	}

	public JButton getButtonRemoveTrain() {
		return buttonRemoveTrain;
	}

	public void setButtonRemoveTrain(JButton buttonRemoveTrain) {
		this.buttonRemoveTrain = buttonRemoveTrain;
	}

	public JButton getButtonStart() {
		return buttonStart;
	}

	public void setButtonStart(JButton buttonStart) {
		this.buttonStart = buttonStart;
	}

	public JButton getButtonSlowSpeed() {
		return buttonSlowSpeed;
	}

	public void setButtonSlowSpeed(JButton buttonSlowSpeed) {
		this.buttonSlowSpeed = buttonSlowSpeed;
	}

	public JButton getButtonFastSpeed() {
		return buttonFastSpeed;
	}

	public void setButtonFastSpeed(JButton buttonFastSpeed) {
		this.buttonFastSpeed = buttonFastSpeed;
	}

	public JButton getButtonStop() {
		return buttonStop;
	}

	public void setButtonStop(JButton buttonStop) {
		this.buttonStop = buttonStop;
	}

	public JButton getButtonClose() {
		return buttonClose;
	}

	public void setButtonClose(JButton buttonClose) {
		this.buttonClose = buttonClose;
	}
	
	
}
