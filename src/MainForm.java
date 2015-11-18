import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class MainForm extends JFrame {

	public static void main(String[] args) {
		new MainForm().setVisible(true);
	}

	private final int FRAME_PADDING = 15;
	private final int BUTTON_SPACING = 10;

	private JButton startButton;
	private JButton stopButton;
	private JButton newDataButton;
	private RectangleViewerLabel label;
	private SpringLayout layout;
	private JTextField field;

	/**
	 * Constructs the JButtons, the JTextField, and adds the JPanel to show the rectangles
	 */
	public MainForm() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		layout = new SpringLayout();
		getContentPane().setLayout(layout);

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		newDataButton = new JButton("New Data");
		label = new RectangleViewerLabel(50);
		field = new JTextField("50", 5);
		

		layout.putConstraint(SpringLayout.SOUTH, stopButton, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, stopButton, 0,
				SpringLayout.HORIZONTAL_CENTER, getContentPane());

		layout.putConstraint(SpringLayout.EAST, startButton, -BUTTON_SPACING,
				SpringLayout.WEST, stopButton);
		layout.putConstraint(SpringLayout.SOUTH, startButton, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());

		layout.putConstraint(SpringLayout.WEST, newDataButton, BUTTON_SPACING,
				SpringLayout.EAST, stopButton);
		layout.putConstraint(SpringLayout.SOUTH, newDataButton, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());

		layout.putConstraint(SpringLayout.NORTH, label, FRAME_PADDING,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.EAST, label, FRAME_PADDING,
				SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.WEST, label, FRAME_PADDING,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, label, -FRAME_PADDING,
				SpringLayout.NORTH, stopButton);
		
		layout.putConstraint(SpringLayout.SOUTH, field, -FRAME_PADDING, SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.WEST, field, BUTTON_SPACING, SpringLayout.EAST, newDataButton);
		

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
		add(field);
	}

	/**
	 * Starts sorting if the sorting hasn't finished
	 * Also disables buttons so bad things wont happen
	 */
	public void startButtonPressed() {
		if (label.startSorting()) {
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			newDataButton.setEnabled(false);
		}
	}

	/**
	 * Stops sorting and puts the buttons back to enabled
	 */
	public void stopButtonPressed() {
		label.stopSorting();
		stopButton.setEnabled(false);
		startButton.setEnabled(true);
		newDataButton.setEnabled(true);
	}

	/**
	 * Creates a new random label with specificed size. Default is 50
	 */
	public void newDataButtonPressed() {
		remove(label);
		int size;
		try{
			size = Integer.parseInt(field.getText());
		} catch (NumberFormatException e){
			size = 50;
		}
		label = new RectangleViewerLabel(size);
		layout.putConstraint(SpringLayout.NORTH, label, FRAME_PADDING,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.EAST, label, FRAME_PADDING,
				SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.WEST, label, FRAME_PADDING,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, label, -FRAME_PADDING,
				SpringLayout.NORTH, stopButton);
		add(label);
		revalidate();
		repaint();
	}
	
	

}
