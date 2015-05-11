import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class GraphicOverlayFrame extends JFrame implements ArrayUpdater {

	public static void main(String[] args){
		new GraphicOverlayFrame().setVisible(true);
	}
	
	private final int FRAME_PADDING = 15;
	private final int BUTTON_SPACING= 10;
	
	private JButton startButton;
	private JButton stopButton;
	private JButton newDataButton;
	private JLabel label;

	private RandomQuicksort quicksort;
	private int[] currentArray;
	
	private Thread sortingThread;

	public GraphicOverlayFrame() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SpringLayout layout = new SpringLayout();
		getContentPane().setLayout(layout);

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		newDataButton = new JButton("New Data");
		label = new JLabel();

		layout.putConstraint(SpringLayout.SOUTH, stopButton, -FRAME_PADDING, SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, stopButton, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());

		layout.putConstraint(SpringLayout.EAST, startButton, -BUTTON_SPACING, SpringLayout.WEST, stopButton);
		layout.putConstraint(SpringLayout.SOUTH, startButton, -FRAME_PADDING, SpringLayout.SOUTH, getContentPane());

		layout.putConstraint(SpringLayout.WEST, newDataButton, BUTTON_SPACING, SpringLayout.EAST, stopButton);
		layout.putConstraint(SpringLayout.SOUTH, newDataButton, -FRAME_PADDING, SpringLayout.SOUTH, getContentPane());
		
		layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, getContentPane());
		
		

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				startButtonPressed();
			}

		});
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stopButtonPressed();
			}

		});
		newDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newDataButtonPressed();
			}

		});

		add(startButton);
		add(stopButton);
		add(newDataButton);
		add(label);

		quicksort = new RandomQuicksort(50, this);
		currentArray = quicksort.getArray();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int baseline = getHeight() - startButton.getHeight() - 2 * FRAME_PADDING;
		double size = quicksort.getSize();
		int width = (int) ((getWidth() - 2* FRAME_PADDING)/ size);
		for (int i = 0; i < currentArray.length; i++) {
			if (i%2 == 0)
				g.setColor(new Color(255, 128, 64));
			else
				g.setColor(Color.RED);
			int x = i * width + FRAME_PADDING;
			int height = (int) (currentArray[i] / size * (baseline - FRAME_PADDING));
			int y = baseline - height;
			g.fillRect(x,y, width, height);

		}
	}

	@Override
	public void update(int[] array) {
		currentArray = array;
		repaint();
	}

	public void startButtonPressed() {
		sortingThread = new Thread() {

			@Override
			public void run() {
				quicksort.sort();
			}

		};
		sortingThread.start();
		startButton.setEnabled(false);
		stopButton.setEnabled(true);
	}

	public void stopButtonPressed() {
		sortingThread.stop();
		stopButton.setEnabled(false);
		startButton.setEnabled(true);
	}

	public void newDataButtonPressed() {
		quicksort = new RandomQuicksort(50, this);
	}

}
