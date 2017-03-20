package labs.lab2.p2;

import java.util.Random;

/**
 * Task 2 Problem 2
 * Execute the TenThreads.java example. Modify the program so that it uses 20 threads and uses the Runnable interface.
 * <p>
 * Why do we need the threads[i].join() line? What will happen if we remove it?
 */


public class TenThreads {
	static int N_THREADS = 20;
	private static class WorkerThread implements Runnable {
		int max = Integer.MIN_VALUE;
		int[] ourArray;

		public WorkerThread(int[] ourArray) {
			this.ourArray = ourArray;
		}

		// Find the maximum value in our particular piece of the array
		public void run() {
			for (int i = 0; i < ourArray.length; i++)
				max = Math.max(max, ourArray[i]);
		}

		public int getMax() {
			return max;
		}
	}

	public static void main(String[] args) {
		WorkerThread[] workerThreads = new WorkerThread[N_THREADS];
		Thread[] threads = new Thread[N_THREADS];
		int[][] bigMatrix = getBigHairyMatrix();
		int max = Integer.MIN_VALUE;

		// Give each thread a slice of the matrix to work with
		for (int i = 0; i < N_THREADS; i++) {
			workerThreads[i] = new WorkerThread(bigMatrix[i]);
			threads[i] = new Thread(workerThreads[i]);
			threads[i].start();
		}

		// Wait for each thread to finish
		try {
			for (int i = 0; i < N_THREADS; i++) {
				threads[i].join(); // why is this needed
				max = Math.max(max, workerThreads[i].getMax());
			}
		} catch (InterruptedException e) {
			// fall through
		}

		System.out.println("Maximum value was " + max);
	}

	static int[][] getBigHairyMatrix() {
		//if 100 it will find only the max of frist 20 rows.
		int x = N_THREADS;
		int y = 100;

		int[][] matrix = new int[x][y];
		Random rnd = new Random();

		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				matrix[i][j] = rnd.nextInt();
			}

		return matrix;
	}
}
