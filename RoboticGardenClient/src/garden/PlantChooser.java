package garden;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import garden.comm.Plant;
import garden.comm.RabbitConnection;

public class PlantChooser {

	private static RabbitConnection _connection;
	private static int planterNum;
	
	private static JFrame frame;

	public static void choosePlant(int p, RabbitConnection callback) {
		_connection = callback;
		planterNum = p;
		frame = new JFrame("Plant Chooser");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new ListDemo();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int) (screen.getWidth() / 2 - frame.getWidth() / 2),
				(int) screen.getHeight() / 2 - frame.getHeight() / 2);
		frame.setVisible(true);
	}

	static class ListDemo extends JPanel implements ListSelectionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7458035983539531012L;
		private JList list;
		private DefaultListModel listModel;

		private static final String actionString = "Replant this plant.";
		private JButton actionButton;

		public ListDemo() {
			super(new BorderLayout());

			listModel = new DefaultListModel();
			for (Plant.Type p : Plant.Type.values()) {
				listModel.addElement(p.name());
			}

			// Create the list and put it in a scroll pane.
			list = new JList(listModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setSelectedIndex(0);
			list.addListSelectionListener(this);
			list.setVisibleRowCount(5);
			JScrollPane listScrollPane = new JScrollPane(list);

			actionButton = new JButton(actionString);
			actionButton.setActionCommand(actionString);
			actionButton.addActionListener(new ActionButtonListener());

			// Create a panel that uses BoxLayout.
			JPanel buttonPane = new JPanel();
			buttonPane
					.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
			buttonPane.add(actionButton);
			buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			add(listScrollPane, BorderLayout.CENTER);
			add(buttonPane, BorderLayout.PAGE_END);
		}

		class ActionButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();

				try {
					_connection.replant(planterNum, Plant.Type.values()[index]);
				} catch (IOException e1) {
					System.err.println("Error communication with rabbit.");
					e1.printStackTrace();
					System.exit(0);
				}
				frame.dispose();
			}
		}

		// This method is required by ListSelectionListener.
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {

				if (list.getSelectedIndex() == -1) {
					// No selection, disable fire button.
					actionButton.setEnabled(false);

				} else {
					// Selection, enable the fire button.
					actionButton.setEnabled(true);
				}
			}
		}

	};
}
