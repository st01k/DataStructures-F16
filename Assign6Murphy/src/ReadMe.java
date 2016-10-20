import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Locates associated README.txt and prints it.
 * @author Casey J. Murphy
 * @version 1.0
 */
public class ReadMe {
	
	/**
	 * Prints README.txt with chosen method.
	 * @throws IOException
	 */
	public static void printReadMe() throws IOException {
		
		printWithBuffReader();
	}
	
	/**
	 * Optional way to print README.txt
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void printWithBuffReader() throws FileNotFoundException, IOException {
		
		String path = buildPath() + "README.txt";
		
		try (BufferedReader br = new BufferedReader (new FileReader(path))) {
			
			out.println(path);
			out.println();
			
			String line = null;
			
			while ((line = br.readLine()) != null) {
				out.println(line);
			}
			out.println();
		}
		
		catch (FileNotFoundException fnfe) {
			
			out.println("File Not Found.");
			out.println("If running from a .jar file, make sure README.txt is located in a subfolder named, 'receivables'" +
						"\nand that folder is in the same directory as the Assign6Murphy.jar file.");
			out.println();
		}
	}
	
	/**
	 * Optional way to print README.txt file.
	 */
	public static void printWithScanner() {
		
		try {
			
			Scanner file = new Scanner(new File(buildPath() + "README.txt"));
			out.println();
			while (file.hasNextLine()) out.println(file.nextLine());
			out.println();
			file.close();
		}
		
		catch (FileNotFoundException fnfe) {
			out.println("File not found.");
			out.println("If running from a .jar file, make sure README.txt is located in a subfolder named, 'receivables'" +
						"\nand that folder is in the same directory as the Assign6Murphy.jar file.");
			out.println();
		}
	}

	/**
	 * Builds path to user's working directory.
	 * @return path
	 */
	private static String buildPath() {
		
		final String SEP = System.getProperty("file.separator");
		final String DOCSFLDR = "receivables";
		
		String path = System.getProperty("user.dir");
		return path += SEP + DOCSFLDR + SEP;
	}
}
