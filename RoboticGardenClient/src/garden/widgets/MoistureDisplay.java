package garden.widgets;

import garden.comm.Moisture;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class MoistureDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 797170251587994961L;
	
	private float _moisture;
	private BufferedImage _image;
	
	public MoistureDisplay() {
		_image = Moisture.getImage(0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(_image, 0, 0, getWidth(), getHeight(), 0, 0,
				_image.getWidth(), _image.getHeight(), null);
		
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

	}
	
	public void setMoisture(float value) {
		_moisture = value;
		_image = Moisture.getImage(_moisture);
		repaint();
	}
}
