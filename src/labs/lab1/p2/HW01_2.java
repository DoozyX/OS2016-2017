package labs.lab1.p2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Task 2 Problem 2
 * Write a Java program which will use I/O streams to read the content of the file source.txt, and then write out its content in a reverse order to the empty file destination.txt. The read and write should be done using streams which work byte-by-byte.
 * <p>
 * Example:
 * <p>
 * source.txt                  destination.txt
 * <p>
 * Operating Systems           smetsyS gnitarepO
 * Note: You should create these two files yourself and fill izvor.txt with some arbitrary content.
 */

public class HW01_2 {
	static void writeToFile(byte[] buffer, Path out) throws IOException{
		byte[] output = new byte[100];
		for (int i = 99; i >= 0; i--) {
			if (buffer[i] == 0 || (char)buffer[i] == '\n') {
				continue;
			}
			output[99-i] = buffer[i];

		}
		Files.write(out, output, StandardOpenOption.APPEND);
	}


	public static void main(String[] args) throws IOException{
		FileInputStream in = null;

		try {
			in = new FileInputStream("source.txt");
			byte[] buffer = new byte[100];
			Path out = Paths.get("destination.txt");
			Files.write(out, new byte[1]);
			while (in.read(buffer) != -1) {
				writeToFile(buffer, out);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}