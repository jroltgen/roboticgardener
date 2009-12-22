package garden;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import garden.widgets.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Planter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -127271935278457306L;
	
	public static int WIDTH = 400;
	public static int HEIGHT = 200;

	private PlantTypeDisplay _plantTypeDisplay;
	private GdhDisplay _gdhDisplay;
	private MoistureDisplay _moistureDisplay;
	private TemperatureDisplay _temperatureDisplay;
	private WeatherDisplay _weatherDisplay;
	private int planterID;
	private JLabel planterText;
	
	public Planter() {
		initPanel();
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		setSize(WIDTH, HEIGHT);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(0, 0, getWidth(), getHeight());
	}
	
	
}
