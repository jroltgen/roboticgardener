package garden.comm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Moisture {
	
	public static BufferedImage getImage(float f) {
		BufferedImage ret = null;
		try {
			if (f > 0.5) {
				ret = ImageIO.read(new File("images/moisture/wet.gif"));
			} else {
				ret = ImageIO.read(new File("images/moisture/dry.gif"));
			}
		} catch (IOException e) {
			System.err.println("IO error reading file.");
			e.printStackTrace();
			System.exit(0);
		}

		return ret;
	}
}
