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

	private static final String SEP = System.getProperty("file.separator");
	private static final String DOCSFLDR = "receivables";
	
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
		
		try (BufferedReader br = new BufferedReader (new FileReader(buildPath() + "README.txt"))) {
			
			String line = null;
			
			out.println();
			while ((line = br.readLine()) != null) {
				out.println(line);
			}
			out.println();
		}
		
		catch (FileNotFoundException fnfe) {
			
			out.println("File Not Found.");
			out.println("If running from a .jar file, make sure .jar is inside of project folder.");
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
			fnfe.printStackTrace();
		}
	}

	/**
	 * Builds path to user's working directory.
	 * @return path
	 */
	private static String buildPath() {
		
		String p = System.getProperty("user.dir");
		out.println(p);
		return p += SEP + DOCSFLDR + SEP;
	}
}
