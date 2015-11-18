import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class RectangleViewerLabel extends JLabel implements RandomQuicksort.ArrayUpdater {

	private RandomQuicksort quicksort;
	private int[] currentArray;
	private Thread updateThread;

	private boolean isFinished = false;

	/**
	 * Constructs the label with rectangles
	 * 
	 * @param size
	 *            the number of rectangles to display
	 */
	public RectangleViewerLabel(int size) {
		quicksort = new RandomQuicksort(size, this);
		currentArray = quicksort.getArray();
		updateThread = new Thread() {

			@Override
			public void run() {
				quicksort.sort();
				isFinished = true;
			}

		};
	}

	/**
	 * Fills the label with rectangles based on the size of the label
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		double size = quicksort.getArray().length;
		int width = (int) (getWidth() / size);
		for (int i = 0; i < currentArray.length; i++) {
			if (i % 2 == 0)
				g.setColor(new Color(255, 128, 64));
			else
				g.setColor(Color.RED);
			int x = i * width;
			int height = (int) (currentArray[i] / size * getHeight());
			int y = getHeight() - height;
			g.fillRect(x, y, width, height);
		}
	}

	/**
	 * Used to update the array
	 */
	@Override
	public void update(int[] array) {
		currentArray = array;
		repaint();
	}

	/**
	 * Starts the sorting thread if it hasn't finished
	 * 
	 * @return whether it was successful
	 */
	public boolean startSorting() {
		if (isFinished)
			return false;
		if (!updateThread.isAlive())
			updateThread.start();
		else
			updateThread.resume();
		return true;
	}

	/**
	 * Pauses the sorting thread
	 */
	public void stopSorting() {
		updateThread.suspend();
	}

}
