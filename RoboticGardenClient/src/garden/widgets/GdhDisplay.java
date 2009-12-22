package garden.widgets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class GdhDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3454447553078105929L;

	private float progress;
	
	public GdhDisplay(float progress) {
		this.progress = progress;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// Draw progress
		g2.setColor(Color.GREEN);
		g2.fillRect(0, 0, (int)(getWidth() * progress), getHeight());
		
		// Draw border.
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(0, 0, getWidth(), getHeight());
	}
	
	public void setProgress(float progress) {
		this.progress = progress;
		repaint();
	}
}
