package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.SimulationGUI;

/**
 * @author Karim
 *
 */
public class WelcomPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final int width = 400;
	private final int height = 150;

	private JFrame firstpage = new JFrame();
	
	private JPanel top = new JPanel();
	private JPanel right = new JPanel();
	private JPanel mid = new JPanel();
	private JPanel left = new JPanel();
	private JPanel bot = new JPanel();
	
	private JButton start = new JButton("Lancer la simulation");
	private JButton exit = new JButton("Quitter");
	
	@SuppressWarnings("unused")
	private Image bg;
	
	public WelcomPage(){
		firstpage.setTitle("BIENVENUE SUR ENTROTRAIN");
		firstpage.setSize(width, height);
		firstpage.setLocationRelativeTo(null);
		firstpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JLabel w1 = new JLabel("");
	    JLabel w2 = new JLabel("");
	    
	    start.addActionListener(this);
	    exit.addActionListener(this);
	    
	    start.setPreferredSize(new Dimension(200, 50));
	    exit.setPreferredSize(new Dimension(150, 50));
	    
	    mid.setPreferredSize(new Dimension(width, height));
	    mid.add(w2);
	    
	    bot.setPreferredSize(new Dimension(width, height-90));
	    bot.add(start);
	    bot.add(w1);
	    bot.add(exit);
	    
	    this.setPreferredSize(new Dimension(width, height));
	    this.setLayout(new BorderLayout());
	    this.add(top, BorderLayout.NORTH);
	    this.add(right, BorderLayout.EAST);
	    this.add(mid, BorderLayout.CENTER);
	    this.add(left, BorderLayout.WEST);
	    this.add(bot, BorderLayout.SOUTH);
	    
	    //background();
	    
	    firstpage.getContentPane().add(this);
	    firstpage.setVisible(true);
	    firstpage.repaint();
	}
	
	/*public void background(){		
		try {
			bg = ImageIO.read(new File(getClass().getResource("/CONFIG/Grey.jpg").toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}*/
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(start)) {
			firstpage.dispose();
			SimulationGUI simulation = new SimulationGUI();
			Thread thread = new Thread(simulation);
			thread.start();
		}
		else if (arg0.getSource().equals(exit)) {
			System.exit(0);
		}
		else {
			System.out.println("Error");
			System.exit(-1);
		}
	}
}