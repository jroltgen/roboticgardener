package garden;

import garden.comm.RabbitConnection;
import garden.widgets.TitleImage;

import java.awt.Dimension;
import java.awt.Toolkit;
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
		//GardenStateMessage s = null;
		/* TODO communication try {
			s = _connection.getGardenState();
		} catch (IOException e) {
			System.err.println("Error retreiving garden state.");
			e.printStackTrace();
			System.exit(0);
		}
		*/
		initFrame(2/* TODO communication s.getNumPlanters()*/);

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
		t.setLocation(0, 0);
		t.setSize(80, HEIGHT - 30);
		myFrame.add(t);
		
		
		//
		//JPanel planterPanel = new JPanel();
		
		//JScrollPane sp = new JScrollPane();

		//sp.setLayout(null);
		//planterPanel.setLayout(null);
		
		
		//planterPanel.setLocation(0, 0);
		//planterPanel.setSize(WIDTH - 150, HEIGHT * 20);
		//planterPanel.setBackground(Color.GREEN);
		for (int i = 0; i < numPlanters; i++) {
			Planter p = new Planter(_connection);
			p.setLocation(90, 10 + i * (10 + Planter.HEIGHT));
			System.out.println(p.getSize());
			System.out.println(p.getLocation());
			myFrame.add(p);
			_planters.add(p);
		}
		
		
		//sp.add(planterPanel);
		//sp.setLocation(80, 0);
		//sp.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT - 30));
		//sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//myFrame.add(sp);
		//System.out.println(sp.getSize());
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
