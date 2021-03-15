package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel {
	private int delay = 8;
	private Timer time;
	
	public Gameplay() {
//		time = new Timer();
//		time.start();
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1, 1, 1000, 600);
		
		g.setColor(Color.CYAN);
		g.fillOval(500, 300, 20, 20);
		
		g.dispose();
	}
}
