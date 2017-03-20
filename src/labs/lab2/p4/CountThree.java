package labs.lab2.p4;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Task 4 Problem 4
 * Solve the issue of detecting the number of occurrences of the number 3 in a large array by using thread synchronization methods.
 * The counts are written / incremented in the global variable count on each find.
 * <p>
 * The standard sequential solution is not acceptable as it takes a long time (because the array is very large).
 * Therefore, you need to implement this process and write a method which will count the occurrences of 3 in smaller fragments of the array,
 * while the result is still kept in the global count variable.
 * <p>
 * Note: The starting code for the solutions is given in CountThree.java. You need to test it with an array of at least 1.000 elements.
 */


public class CountThree {

	public static int NUM_RUNS = 100;
	/**
	 * Global variable which has to contain the total number of occurrences of 3
	 */
	int count;

	/**
	 * TODO: define the elements necessary for the scenario
	 */
	private static final Object monitor = new Object();

	public void init() {
		// implement this
		count = 0;
	}

	class Counter extends Thread {
		public void count(int[] data) throws InterruptedException {
			// implement this
			int sum = 0;
			for (int i = 0; i < data.length; i++) {
				if (data[i] == 3) {
					sum++;
				}
			}

			synchronized (monitor) {
				count += sum;
			}
		}

		private int[] data;

		public Counter(int[] data) {
			this.data = data;
		}

		@Override
		public void run() {
			try {
				count(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			CountThree environment = new CountThree();
			environment.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void start() throws Exception {

		init();

		HashSet<Thread> threads = new HashSet<>();
		Scanner s = new Scanner(System.in);
		int total = s.nextInt();

		for (int i = 0; i < NUM_RUNS; i++) {
			int[] data = new int[total];
			for (int j = 0; j < total; j++) {
				data[j] = s.nextInt();
			}
			Counter c = new Counter(data);
			threads.add(c);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
		System.out.println(count);


	}
}
