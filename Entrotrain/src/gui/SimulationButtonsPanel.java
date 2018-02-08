package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SimulationButtonsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton buttonStop;
	
	public SimulationButtonsPanel() {
		// Set the panel 
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new FlowLayout(5));
		
		// Set the button
		buttonStop = new JButton("Stop");
		buttonStop.setBackground(Color.GREEN);
		buttonStop.setForeground(Color.WHITE);
		buttonStop.setFont(new Font("Calibri", Font.PLAIN, 14));
		buttonStop.setUI(new ButtonDesign());
		
		// Add the button the to panel
		add(buttonStop);
	}
}
