package labs.lab2.p3;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * Task 3 Problem 3 (0 / 0)
 * Write a program which will allow the execution of the f1() and f2() functions alternately (one after the other, in an alternating fashion).
 * Their calls should be implemented in threads, such that the first thread will call the f1() function non-stop, and the second thread will call f2() function non-stop.
 * Apart from securing that the functions will be called alternately, you need to enable the execution of f1() more times (extra times) over f2(), while this number is not greater than N.
 */

public class Alternately {

	public static int NUM_RUNS = 1000;

	private int f1count;
	private int f2count;
	private int maxDifference;
	private int difference;

	public Semaphore f1;
	private static final Object monitor = new Object();

	public Alternately() {
		this.maxDifference = -1;
	}

	/**
	 * A method for initializing the semaphore values and
	 * the other variables for synchronization.
	 */
	public void init(int count) {
		// implement this
		f1 = new Semaphore(count);
		difference = count;
	}

	class F1Thread extends Thread {

		public void executeF1() throws InterruptedException {
			// implement this
			f1.acquire();
			synchronized (monitor) {
				f1();
			}
		}

		@Override
		public void run() {
			try {
				executeF1();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class F2Thread extends Thread {

		public void executeF2() throws InterruptedException {
			// implement this
			synchronized (monitor) {
				f2();
			}
			f1.release();
		}

		@Override
		public void run() {
			try {
				executeF2();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void f1() {
		System.out.println("f1()");
		f1count++;
		if (f1count - f2count > maxDifference) {
			maxDifference = f1count - f2count;
		}
	}

	public void f2() {
		System.out.println("f2()");
		f2count++;

		if (f1count - f2count > maxDifference) {
			maxDifference = f1count - f2count;
		}
	}

	public static void main(String[] args) {
		try {
			Alternately environment = new Alternately();
			environment.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void start() throws Exception {

		System.out.println("Enter the number of extra executions of f1()");

		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		init(n);

		HashSet<Thread> threads = new HashSet<>();
		for (int i = 0; i < NUM_RUNS; i++) {
			F1Thread f1 = new F1Thread();
			F2Thread f2 = new F2Thread();
			threads.add(f1);
			threads.add(f2);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
		System.out.println("F1count: " + f1count);
		System.out.println("F2count: " + f2count);
		System.out.println("maxDifference: " + maxDifference);
		System.out.println("Status: " + (maxDifference <= n));
	}
}
