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

	private JButton buttonStart;
	private JButton buttonPause;
	private JButton buttonSlowSpeed;
	private JButton buttonFastSpeed;
	
	public SimulationButtonsPanel() {
		// Set the panel 
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new FlowLayout(5));
		
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
		
		// Add the button the to panel
		add(buttonStart);
		add(buttonPause);
		add(buttonSlowSpeed);
		add(buttonFastSpeed);
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
	
}
