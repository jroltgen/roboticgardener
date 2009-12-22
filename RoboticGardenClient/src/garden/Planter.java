package garden;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import garden.comm.Plant;
import garden.comm.PlanterState;
import garden.comm.RabbitConnection;
import garden.widgets.*;

import javax.swing.JButton;
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
	private LightDisplay _lightDisplay;
	private int planterID;
	private JLabel planterText;
	private JLabel plantTypeText;
	private RabbitConnection _connection;
	
	public Planter(RabbitConnection rc) {
		_connection = rc;
		initPanel();
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		
		planterText = new JLabel("Window Planter #3");
		planterText.setLocation(100, 20);
		planterText.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		planterText.setSize(WIDTH - 20, 20);
		add(planterText);
		
		plantTypeText = new JLabel("Unnamed palnt");
		plantTypeText.setText(Plant.getText(Plant.Type.RADISH));
		plantTypeText.setLocation(100, 50);
		plantTypeText.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
		plantTypeText.setSize(WIDTH - 20, 20);
		add(plantTypeText);
		
		_plantTypeDisplay = new PlantTypeDisplay();
		_plantTypeDisplay.setPlantType(Plant.Type.RADISH);
		_plantTypeDisplay.setLocation(20, 20);
		_plantTypeDisplay.setSize(60, 60);
		add(_plantTypeDisplay);
		
		JLabel gdhLabel = new JLabel("GDH PROGRESS: ");
		gdhLabel.setLocation(20, 95);
		gdhLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
		gdhLabel.setSize(WIDTH - 50, 15);
		add(gdhLabel);
		
		_gdhDisplay = new GdhDisplay(0.3f);
		_gdhDisplay.setLocation(150, 95);
		_gdhDisplay.setSize(WIDTH - 180, 15);
		add(_gdhDisplay);
		
		_temperatureDisplay = new TemperatureDisplay(69.3f);
		_temperatureDisplay.setLocation(20, 130);
		_temperatureDisplay.setSize(50, 50);
		add(_temperatureDisplay);
		
		_lightDisplay = new LightDisplay();
		_lightDisplay.setLocation(90, 130);
		_lightDisplay.setSize(50, 50);
		add(_lightDisplay);
		
		_moistureDisplay = new MoistureDisplay();
		_moistureDisplay.setLocation(160, 130);
		_moistureDisplay.setSize(50, 50);
		add(_moistureDisplay);
		
		JButton waterButton = new JButton("Water");
		waterButton.setLocation(215, 130);
		waterButton.setSize(90, 50);
		waterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				forceWater();
			}
		});
		add(waterButton);
		
		JButton replantButton = new JButton("Replant");
		replantButton.setLocation(305, 130);
		replantButton.setSize(90, 50);
		replantButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				replant();
			}
		});
		add(replantButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(0, 0, getWidth(), getHeight());
		System.out.println("painting...");
	}
	
	private void forceWater() {
		// TODO Auto-generated method stub
		
		System.out.println("Watering");
		try {
			_connection.water(planterID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void updateState(PlanterState s) {
		planterText.setText("Planter " + s.getID());
		
	}

	private void replant() {
		// TODO Auto-generated method stub
		System.out.println("Replanting");
		try {
			_connection.replant(planterID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
}
