package garden.widgets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class TemperatureDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -162644735484402232L;

	private float _currentTemp;

	public TemperatureDisplay(float temp) {
		_currentTemp = temp;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(255, 128, 0));
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

		g2.setColor(Color.WHITE);
		g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		g2.drawString("Temp:", 8, getHeight() - 30);
		g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		g2.drawString((int) _currentTemp + "."
				+ (((int) (_currentTemp * 10)) % 10) + '\u00b0', 5, getHeight() - 10);
	}
	
	public void setTemperature(float temp) {
		_currentTemp = temp;
		repaint();
	}
}
