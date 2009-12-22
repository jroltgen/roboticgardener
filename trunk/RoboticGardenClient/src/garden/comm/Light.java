package garden.comm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Light {

	public enum State {
		SUNNY, PARTLY_CLOUDY, CLOUDY, DARK, NIGHT
	}

	public BufferedImage getIcon(State s) {
		BufferedImage ret = null;
		try {
			switch (s) {
			case SUNNY:
				ret = ImageIO.read(new File("images/light/sunny.gif"));
				break;
				
			case PARTLY_CLOUDY:
				ret = ImageIO.read(new File("images/light/partlycloudy.gif"));
				break;

			case CLOUDY:
				ret = ImageIO.read(new File("images/light/partlysunny.gif"));
				break;
				
			case DARK:
				ret = ImageIO.read(new File("images/light/cloudy.gif"));
				break;
				
			case NIGHT:
				ret = ImageIO.read(new File("images/light/nt_clear.gif"));
				break;
			default:
				System.err.println("No light icon found for " + s);
			}
		} catch (IOException e) {
			System.err.println("Error reading light file.");
			e.printStackTrace();
			System.exit(0);
		}
		return ret;
	}
}
