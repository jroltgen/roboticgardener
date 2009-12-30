package garden.widgets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class WaterBucketDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2229802526482580933L;
	
	private boolean isFull;
	
	public WaterBucketDisplay(boolean level) {
		this.isFull = level;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// Draw background.
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw progress
		g2.setColor(Color.BLUE);
		int height;
		if (isFull) height = (int) (getHeight() * 0.90);
		else height = (int) (getHeight() * 0.1);
		g2.fillRect(0, getHeight() - height, getWidth(), getHeight());
		
		// Draw border.
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		
		g2.rotate(-Math.PI / 2);
		g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g2.drawString("WATER LEVEL", -getHeight() / 2 - 100, getWidth() / 2 + 10);
	}
	
	public void setEmpty() {
		this.isFull = false;
		repaint();
	}
	
	public void setFull() {
		this.isFull = true;
		repaint();
	}
}
