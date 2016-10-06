package fr.subercaze.progressbar;

import java.util.concurrent.TimeUnit;

import javax.swing.text.NumberFormatter;

/**
 * A console progress bar to display the advances of a task.
 * 
 * Sample usage: create a new instance, start the timer and update the number of
 * finished subtasks
 * 
 * @author Julien
 *
 */
public class ConsoleProgressBar {

	/**
	 * Convert ns to ms
	 */
	private static final int NANO_TO_MILLI = 1000000;
	/**
	 * Number of tasks to be performed
	 */
	int subtasks;
	/**
	 * Number of finisehd tasks
	 */
	int finished;

	/**
	 * Width of the progress bar, adapated for 80 chars
	 */
	int width = 40;

	/**
	 * When the task has been started
	 */
	long startTime;

	/**
	 * Display the estimated time of completion
	 */
	boolean displayETA = true;

	/**
	 * Format numbers to avoid the bar moving on the side
	 */
	NumberFormatter formatter;
	/**
	 * Indicates wether the task has been completed
	 */
	private boolean terminated;

	/**
	 * Display chars
	 */
	char startChar = '[', endChar = ']', barChar = '=', emptyChar = ' ';

	/**
	 * 
	 * @param subtasks
	 *            the number of subtasks to be done
	 */
	public ConsoleProgressBar(int subtasks) {
		super();
		this.subtasks = subtasks;
		// Set a default value in case of
		this.startTime = System.nanoTime();
	}

	/**
	 * Reset the starting time
	 */
	public void resetStartTime() {
		this.startTime = System.nanoTime();
	}

	public void startAndDisplay() {
		boolean oldeta = displayETA;
		displayETA = false;
		updateAndDisplay(0);
		displayETA = oldeta;
	}

	/**
	 * Update the internal state of the progress bar and display the updated bar
	 * on the console
	 * 
	 * @param newlyDoneTasks
	 *            the number of substasks that have been completed since the
	 *            previous call to this method
	 */
	public void updateAndDisplay(int newlyDoneTasks) {
		if (terminated) {
			throw new RuntimeException("This progressbar is already complete");
		}
		if (newlyDoneTasks < 0) {
			throw new RuntimeException("Cannot add a negative number of finished substasks");
		}
		if (finished + newlyDoneTasks >= subtasks) {
			System.out.println();
			System.out.println("Elapsed time" + formatInterval((System.nanoTime() - startTime) / NANO_TO_MILLI));
			this.terminated = true;
		} else {
			finished += newlyDoneTasks;
			// Compute the number of chars to display

			int chars = (int) (((double) finished / subtasks) * width);
			// Call display
			clearLine();
			System.out.print(startChar);
			for (int i = 0; i < width; i++) {
				if (i <= chars) {
					System.out.print(barChar);
				} else {
					System.out.print(emptyChar);
				}
			}
			System.out.print(endChar);
			if (displayETA) {
				long msRemaining = (System.nanoTime() - startTime) / (finished) * (subtasks - finished) / NANO_TO_MILLI;
				System.out.print("  -  ETA: " + formatInterval(msRemaining));
			}
		}
	}

	/**
	 * Set the width of the progress bar. Character is the unit.
	 * 
	 * @param charactedWidth
	 */
	public void setWidth(int charactedWidth) {
		this.width = charactedWidth;
	}

	public boolean isDisplayETA() {
		return displayETA;
	}

	public void setDisplayETA(boolean displayETA) {
		this.displayETA = displayETA;
	}

	private void clearLine() {
		System.out.print('\r');
	}

	/**
	 * Taken from Jarrod Roberson
	 * http://stackoverflow.com/questions/6710094/how-to-format-an-elapsed-time-
	 * interval-in-hhmmss-sss-format-in-java
	 * 
	 * @param l
	 * @return
	 */
	private static String formatInterval(final long l) {
		final long hr = TimeUnit.MILLISECONDS.toHours(l);
		final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.MILLISECONDS
				.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
		final long ms = TimeUnit.MILLISECONDS.toMillis(
				l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
		return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public NumberFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(NumberFormatter formatter) {
		this.formatter = formatter;
	}

	public char getStartChar() {
		return startChar;
	}

	public void setStartChar(char startChar) {
		this.startChar = startChar;
	}

	public char getEndChar() {
		return endChar;
	}

	public void setEndChar(char endChar) {
		this.endChar = endChar;
	}

	public char getBarChar() {
		return barChar;
	}

	public void setBarChar(char barChar) {
		this.barChar = barChar;
	}

	public char getEmptyChar() {
		return emptyChar;
	}

	public void setEmptyChar(char emptyChar) {
		this.emptyChar = emptyChar;
	}

	public int getWidth() {
		return width;
	}

}
