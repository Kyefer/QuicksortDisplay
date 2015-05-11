import java.awt.Graphics;

import javax.swing.JLabel;


public class RectangleLabel extends JLabel implements ArrayUpdater	{

	private RandomQuicksort quicksort;
	private int[] currentArray;

	public RectangleLabel(){
		quicksort = new RandomQuicksort(50, this);
		currentArray = quicksort.getArray();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(int[] array) {
		currentArray = array;
		repaint();
	}
	
}
