package garden.comm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plant {

	public static enum Type {
		// TODO add more of these.
		RADISHES, 
		CORN, 
		TOMATOES
	}

	public static BufferedImage getImage(Type t) {
		BufferedImage ret = null;
		try {
			switch (t) {
			case RADISHES:
				ret = ImageIO.read(new File(
						"images/plants/radishcherrybellesmall.jpg"));
				break;
			case CORN:
				ret = ImageIO.read(new File(
						"images/plants/corn1.jpg"));
				break;
			case TOMATOES:
				ret = ImageIO.read(new File(
						"images/plants/tomato.jpg"));
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

	public static String getText(Type t) {
		String ret = null;
		switch (t) {
		case RADISHES:
			ret = "Radishes";
			break;
		case CORN:
			ret = "Corn";
			break;
		case TOMATOES:
			ret = "Tomatoes";
			break;
		default:
			System.err.println("No plant image found for " + t);
			System.exit(0);
			break;
		}
		return ret;
	}
}
