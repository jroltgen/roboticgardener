package garden;

import garden.comm.RabbitConnection;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GardenClient {
	
	public static final int PORT = 3107;

	public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	private RabbitConnection _connection;
	private InitDialog myDialog;
	private JFrame myFrame;
	
	public GardenClient() {
	}
	
	private void run() {
		myFrame = new JFrame("Robotic Garden");
		initFrame();
		
		myDialog = new InitDialog(this);
		myDialog.setVisible(true);
	}
	
	private void initFrame() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new GardenClient().run();
	}

	public boolean connectToGarden(String host) {
		boolean success = _connection.connect(host);
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
