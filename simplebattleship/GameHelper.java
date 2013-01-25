package simplebattleship;
import java.io.*;
public class GameHelper {

	
	public String getUserInput(String query) {
		String inputLine = null;
		System.out.print(query + " ");
		try {
			BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));
				inputLine = is.readLine();
				if (inputLine.length() == 0) {
					return null;
				}
		} catch (IOException e) {
					System.out.println("IOException: " + e);
				}
				return inputLine;
			}
		}
	
