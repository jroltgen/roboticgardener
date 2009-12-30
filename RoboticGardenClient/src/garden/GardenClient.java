package garden;

import garden.comm.GardenStateMessage;
import garden.comm.RabbitConnection;
import garden.widgets.TitleImage;
import garden.widgets.WaterBucketDisplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;

public class GardenClient {

	public static final int PORT = 3107;

	protected static final long UPDATE_RATE = 2000;

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
		System.out.println("Creating connection");
		_connection = new RabbitConnection();

		System.out.println("Running");
		myDialog = new InitDialog(this);
		myDialog.setVisible(true);
	}

	private void initFrame(GardenStateMessage msg) {
		myFrame.setSize(WIDTH, HEIGHT);
		myFrame.setLocation(SCREEN_SIZE.width / 2 - WIDTH / 2,
				SCREEN_SIZE.height / 2 - HEIGHT / 2);

		myFrame.setLayout(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myFrame.getContentPane().setBackground(new Color(10, 115, 10));

		TitleImage t = new TitleImage();
		t.setLocation(0, 0);
		t.setSize(80, HEIGHT - 30);
		myFrame.add(t);

		//
		// JPanel planterPanel = new JPanel();

		// JScrollPane sp = new JScrollPane();

		// sp.setLayout(null);
		// planterPanel.setLayout(null);

		// planterPanel.setLocation(0, 0);
		// planterPanel.setSize(WIDTH - 150, HEIGHT * 20);
		// planterPanel.setBackground(Color.GREEN);
		for (int i = 0; i < msg.getNumPlanters(); i++) {
			Planter p = new Planter(_connection, msg.getPlanterStates().get(i)
					.getID());
			p.updateState(msg.getPlanterStates().get(i));
			p.setLocation(90, 10 + i * (10 + Planter.HEIGHT));
			System.out.println(p.getSize());
			System.out.println(p.getLocation());
			myFrame.add(p);
			_planters.add(p);
		}

		WaterBucketDisplay w = new WaterBucketDisplay(true);
		w.setSize(100, 425);
		w.setLocation(515, 10);
		myFrame.add(w);

		// sp.add(planterPanel);
		// sp.setLocation(80, 0);
		// sp.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT - 30));
		// sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// myFrame.add(sp);
		// System.out.println(sp.getSize());
	}

	public static void main(String[] args) {
		new GardenClient().run();
	}

	public boolean connectToGarden(String host) {
		boolean success;
		// if (host != null) {
		// success = _connection.connect(host);
		// } else {
		// Default IP to connect to.
		success = _connection.connect("129.186.194.31");
		// }
		if (!success) {
			return false;
		} else {
			myDialog.setVisible(false);
			myDialog = null;
			GardenStateMessage s = null;
			try {
				s = _connection.getGardenState();
			} catch (IOException e) {
				System.err.println("Error retreiving garden state.");
				e.printStackTrace();
				System.exit(0);
			}
			if (s == null) {
				throw new RuntimeException("Rotten luck.  The first message "
						+ "failed.  Try starting it again.");
			}
			initFrame(s);
			myFrame.setVisible(true);
			startUpdating();
			return true;
		}
	}

	private void startUpdating() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					updateState();
					try {
						Thread.sleep(UPDATE_RATE);
					} catch (InterruptedException e) {
						System.err.println("Update thread interrupted.");
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
		});
		t.start();
	}

	private void updateState() {
		GardenStateMessage msg = null;
		try {
			msg = _connection.getGardenState();
		} catch (IOException e) {
			System.err.println("IOException in update state");
			e.printStackTrace();
		}
		if (msg == null) {
			// There was an error.  Don't update.
			return;
		}
		for (int i = 0; i < msg.getNumPlanters(); i++) {
			for (Planter p : _planters) {
				if (p.getPlanterID() == msg.getPlanterStates().get(i).getID()) {
					p.updateState(msg.getPlanterStates().get(i));
				}
			}
		}
	}

}
