package garden.widgets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class TitleImage extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1394656905238585893L;

	private BufferedImage _image;

	public TitleImage() {
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
	}

}
