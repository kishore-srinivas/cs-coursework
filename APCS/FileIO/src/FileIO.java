import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	public static final String DELIMITER = System.getProperty("file.separator");

	public static ArrayList<String> readFile(String filename) {
		Scanner in = null;
		try {
			ArrayList<String> output = new ArrayList<String>();
			FileReader reader = new FileReader(filename);
			in = new Scanner(reader);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				output.add(line);
			}
			return output;
		} catch (FileNotFoundException e) {

		} finally {
			if (in != null)
				in.close();
		}
		return null;
	}

	public static void writeFile(String filename, ArrayList<String> fileData) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filename);
			for (String line : fileData) {
				writer.write(line + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)	writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
