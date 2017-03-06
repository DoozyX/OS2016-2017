package labs.lab1.p1;

import java.io.File;

/**
 * Task 1 Problem 1
 * Write a Java program which will show the average size of the all .txt files which are part of the directory denoted as a command line argument.
 * <p>
 * Note: Use the File class to access the content of the directory.
 * <p>
 * Solution: The HW01_1.java file from your solution should be placed here, using copy-paste.
 */

public class HW01_1 {
	private static boolean isTxt(File file) {
		String name = file.getName();
		return name.substring(name.lastIndexOf(".") + 1).equals("txt");
	}

	private static double showAverageSize(File dir) {
		File[] files = dir.listFiles();
		int counter = 0;
		double sum = 0;
		if (files != null) {
			for (File file : files) {
				if (isTxt(file)) {
					++counter;
					sum += file.length();
				}
			}
		}
		return sum / counter;
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			File dir = new File(args[0]);
			if (dir.exists()) {
				System.out.println(showAverageSize(dir));
			}
		} else {
			System.out.println("Enter the directory path in console argument.");
		}
	}
}
