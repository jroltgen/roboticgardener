package garden.comm;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plant {

	public static enum Type {
		// TODO add more of these.
		RADISH, CORN, TOMATO
	}

	public static Image getImage(Type t) {
		BufferedImage ret = null;
		try {
			switch (t) {
			case RADISH:
				ret = ImageIO.read(new File(
						"images/plants/radishcherrybellesmall.jpg"));
				
			case CORN:
			case TOMATO:
			default:
				System.err.println("No plant image found for " + t);
				System.exit(0);
				break;
			}
		} catch (IOException e) {
			System.err.println("IO error reading file.");
			e.printStackTrace();
			System.exit(0);
		}

		return ret;
	}
}
