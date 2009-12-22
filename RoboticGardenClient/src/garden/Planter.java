package garden;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import garden.comm.Plant;
import garden.comm.PlanterState;
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
		setLayout(null);
		
		planterText = new JLabel("Unnamed Planter");
		planterText.setLocation(80, 20);
		planterText.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		planterText.setSize(WIDTH - 20, 15);
		add(planterText);
		
		_plantTypeDisplay = new PlantTypeDisplay();
		_plantTypeDisplay.setPlantType(Plant.Type.RADISH);
		_plantTypeDisplay.setLocation(20, 20);
		_plantTypeDisplay.setSize(50, 50);
		add(_plantTypeDisplay);
		
		JLabel gdhLabel = new JLabel("GDH Progress: ");
		gdhLabel.setLocation(80, 50);
		gdhLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
		gdhLabel.setSize(WIDTH - 50, 15);
		add(gdhLabel);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(0, 0, getWidth(), getHeight());
	}
	
	public void updateState(PlanterState s) {
		planterText.setText("Planter " + s.getID());
		
	}
	
	
}
