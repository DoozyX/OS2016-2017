package labs.lab1.p3;

import java.io.*;

/**
 * Task 3 Problem 3
 * Write a Java program which will use I/O streams to read the content of the file source.txt, and then write out its content in a reverse order to the empty file destination.txt. The read and write should be done using buffered streams.
 * <p>
 * Example:
 * <p>
 * source.txt                  destination.txt
 * <p>
 * Operating Systems           smetsyS gnitarepO
 * Note: You should create these two files yourself and fill izvor.txt with some arbitrary content.
 */

public class HW01_3 {
	static void writeToFile(String line, BufferedWriter out) throws IOException {
		StringBuilder sb = new StringBuilder(line).reverse();
		out.write(sb.toString() + '\n');
		out.flush();
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader("source.txt"));
			bw = new BufferedWriter(new FileWriter("destination.txt"));

			String line;

			while ((line = br.readLine()) != null) {
				writeToFile(line, bw);
			}
		} finally {
			if (br != null) {
				br.close();
			}
			if (bw != null) {
				bw.close();
			}
		}
	}
}