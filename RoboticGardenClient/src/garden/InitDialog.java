package garden;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InitDialog extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7594849354294375644L;

	private static final int BORDER = 10;
	private static final int HEIGHT = 200;
	private static final int WIDTH = 300;

	private GardenClient _client;
	private JTextField input;

	public InitDialog(GardenClient c) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_client = c;
		setLayout(null);
		setLocation(GardenClient.SCREEN_SIZE.width / 2 - WIDTH / 2,
				GardenClient.SCREEN_SIZE.height / 2 - HEIGHT / 2);
		setSize(WIDTH, HEIGHT);
		setBackground(Color.GREEN);

		JLabel text = new JLabel("Please enter the URL or IP of the");
		JLabel text2 = new JLabel("garden you wish to connect to.");
		text.setLocation(BORDER, BORDER / 2);
		text.setSize(WIDTH - 2 * BORDER, 30);
		add(text);

		text2.setLocation(BORDER, BORDER / 2 + 15);
		text2.setSize(WIDTH - 2 * BORDER, 30);
		add(text2);

		input = new JTextField();
		input.setLocation(BORDER, 60);
		input.setSize(WIDTH - BORDER / 2 - (100), 20);
		add(input);

		JButton submitButton = new JButton("Connect!");
		submitButton.setLocation(BORDER, 90);
		submitButton.setSize(WIDTH - BORDER * 4, 50);
		submitButton.addActionListener(this);
		add(submitButton);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (_client.connectToGarden(input.getText())) {
			// Hooray! The Client will dispose of us.
		} else {
			JOptionPane.showMessageDialog(null, "Couldn't connect to garden. "
					+ "Please check the IP you provided.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
