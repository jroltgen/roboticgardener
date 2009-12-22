package garden.widgets;

import garden.comm.Light;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class LightDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4181889400309347009L;
	private BufferedImage _image;
	private Light.State type;

	public LightDisplay() {
		_image = Light.getIcon(Light.State.CLOUDY);
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
	
	public void setLight(Light.State s) {
		type = s;
		_image = Light.getIcon(type);
		repaint();
	}
}
