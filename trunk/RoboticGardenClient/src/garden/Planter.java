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
	
	public static int WIDTH = 410;
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
	
	public Planter(RabbitConnection rc, int id) {
		_connection = rc;
		initPanel();
		planterID = id;
	}

	private void initPanel() {
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		
		planterText = new JLabel("Window Planter #3");
		planterText.setLocation(100, 22);
		planterText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		planterText.setSize(WIDTH - 20, 20);
		add(planterText);
		
		plantTypeText = new JLabel("Unnamed palnt");
		plantTypeText.setText(Plant.getText(Plant.Type.RADISHES));
		plantTypeText.setLocation(100, 45);
		plantTypeText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		plantTypeText.setSize(WIDTH - 20, 20);
		add(plantTypeText);
		
		_plantTypeDisplay = new PlantTypeDisplay();
		_plantTypeDisplay.setPlantType(Plant.Type.RADISHES);
		_plantTypeDisplay.setLocation(20, 20);
		_plantTypeDisplay.setSize(60, 60);
		add(_plantTypeDisplay);
		
		JLabel gdhLabel = new JLabel("GDD Progress: ");
		gdhLabel.setLocation(20, 92);
		gdhLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		gdhLabel.setSize(WIDTH - 50, 20);
		add(gdhLabel);
		
		_gdhDisplay = new GdhDisplay(0.3f);
		_gdhDisplay.setLocation(130, 95);
		_gdhDisplay.setSize(180, 15);
		add(_gdhDisplay);
		
		_temperatureDisplay = new TemperatureDisplay(69.3f);
		_temperatureDisplay.setLocation(345, 15);
		_temperatureDisplay.setSize(50, 50);
		add(_temperatureDisplay);
		
		_lightDisplay = new LightDisplay();
		_lightDisplay.setLocation(345, 75);
		_lightDisplay.setSize(50, 50);
		add(_lightDisplay);
		
		_moistureDisplay = new MoistureDisplay();
		_moistureDisplay.setLocation(345, 135);
		_moistureDisplay.setSize(50, 50);
		add(_moistureDisplay);
		
		JButton waterButton = new JButton("Water");
		waterButton.setLocation(65, 145);
		waterButton.setSize(100, 30);
		waterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				forceWater();
			}
		});
		add(waterButton);
		
		JButton replantButton = new JButton("Replant");
		replantButton.setLocation(180, 145);
		replantButton.setSize(100, 30);
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
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	private void forceWater() {
		
		System.out.println("Watering");
		try {
			_connection.water(planterID);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void updateState(PlanterState s) {
		/* TODO connection 
		planterText.setText("Planter " + s.getID());
		plantTypeText.setText(s.getPlantType().name());
		_gdhDisplay.setProgress(s.getGdhProgress());
		_lightDisplay.setLight(s.getLightState());
		_moistureDisplay.setMoisture(s.getMoistureLevel());
		_plantTypeDisplay.setPlantType(s.getPlantType());
		_temperatureDisplay.setTemperature(s.getTemperature());*/
	}

	private void replant() {
		System.out.println("Replanting");
		PlantChooser.choosePlant(this.getPlanterID(), _connection);
	}
	
	public int getPlanterID() {
		return planterID;
	}
}
