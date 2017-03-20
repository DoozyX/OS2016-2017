package labs.lab2.p1;

/**
 * Execute the TwoThreads.java example. Then modify the program so that it uses only one thread class, ThreadAB.
 * In the constructor of the class you should pass the two strings which should be printed by the corresponding instance.
 * The thread should not inherit from the Thread class. The new program should behave the same way as the original one,
 * i.e. you must have two threads again which will separately execute the run() method: one thread will print A and B, while the other one will print 1 and 2.
 * <p>
 * What would happen if one thread needs to print out the entire alphabet, and the other the numbers from 1 to 26? Can you predict the program output correctly?
 */

public class TwoThreads {
	public static class THreadAB implements Runnable{
		String first;
		String second;

		public THreadAB(String first, String second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public void run() {
			System.out.println(first);
			System.out.println(second);
		}
	}

/*
	public static class Thread1 extends Thread {
		public void run() {
			System.out.println("A");
			System.out.println("B");
		}
	}

	public static class Thread2 extends Thread {
		public void run() {
			System.out.println("1");
			System.out.println("2");
		}
	}
*/

	public static void main(String[] args) {
/*		new Thread1().start();
		new Thread2().start();*/
		new Thread(new THreadAB("A","B")).start();
		new Thread(new THreadAB("1","2")).start();
}

}
