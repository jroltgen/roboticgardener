package garden;

import garden.comm.GardenStateMessage;
import garden.comm.RabbitConnection;
import garden.widgets.TitleImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

public class GardenClient {

	public static final int PORT = 3107;

	public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit()
			.getScreenSize();
	
	private static int WIDTH = 640;
	private static int HEIGHT = 480;

	private RabbitConnection _connection;
	private InitDialog myDialog;
	private JFrame myFrame;
	private Vector<Planter> _planters;
	// TODO add scroll pane eventually.
	
	

	public GardenClient() {
		_planters = new Vector<Planter>();
	}

	private void run() {
		myFrame = new JFrame("Robotic Garden Monitoring Station");
		_connection = new RabbitConnection();
		// TODO communication _connection.connect("put the host name here.");
		GardenStateMessage s = null;
		/* TODO communication try {
			s = _connection.getGardenState();
		} catch (IOException e) {
			System.err.println("Error retreiving garden state.");
			e.printStackTrace();
			System.exit(0);
		}
		*/
		initFrame(3/* TODO communication s.getNumPlanters()*/);

		myDialog = new InitDialog(this);
		myDialog.setVisible(true);
	}

	private void initFrame(int numPlanters) {
		// TODO Auto-generated method stub
		myFrame.setSize(WIDTH, HEIGHT);
		myFrame.setLocation(SCREEN_SIZE.width / 2 - WIDTH / 2,
				SCREEN_SIZE.height / 2 - HEIGHT / 2);
		
		myFrame.setLayout(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TitleImage t = new TitleImage();
		
		myFrame.getContentPane().setBackground(Color.GREEN);
		
		t.setLocation(0, 0);
		t.setSize(80, HEIGHT - 30);
		myFrame.add(t);
		
		for (int i = 0; i < numPlanters; i++) {
			Planter p = new Planter();
			p.setLocation(100, 10 + i * (10 + Planter.HEIGHT));
			myFrame.add(p);
			_planters.add(p);
		}
		
	}

	public static void main(String[] args) {
		new GardenClient().run();
	}

	public boolean connectToGarden(String host) {
		// TODO communication boolean success = _connection.connect(host);
		boolean success = true;
		if (!success) {
			return false;
		} else {
			myDialog.setVisible(false);
			myDialog = null;
			myFrame.setVisible(true);
			return true;
		}
	}

}
