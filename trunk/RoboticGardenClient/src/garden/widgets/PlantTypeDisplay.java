package garden.widgets;

import garden.comm.Plant;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PlantTypeDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8379315439099796742L;

	private BufferedImage _image;
	private Plant.Type type;

	public PlantTypeDisplay() {
		try {
			_image = ImageIO.read(new File("images/title.png"));
		} catch (IOException e) {
			System.err.println("Problem reading file images/title.png");
			e.printStackTrace();
			System.exit(0);
		}
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
	
	public void setPlantType(Plant.Type t) {
		type = t;
		_image = Plant.getImage(type);
		repaint();
	}

}
