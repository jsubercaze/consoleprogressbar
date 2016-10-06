package fr.subercaze.progressbar;

import java.util.Random;

public class Example {

	public static void main(String[] args) throws InterruptedException {
		int tasks = 100;
		int increment = 1;
		int maxrandomTime = 2000;
		int minrandomTime = 500;
		Random rand = new Random();
		// Initialize the progressbar
		ConsoleProgressBar bar = new ConsoleProgressBar(tasks);
		bar.startAndDisplay();
		for (int i = 0; i < tasks; i += increment) {
			bar.updateAndDisplay(increment);
			Thread.sleep(minrandomTime + rand.nextInt(maxrandomTime - minrandomTime));
		}
	}
}
