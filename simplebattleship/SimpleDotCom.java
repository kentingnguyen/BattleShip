package simplebattleship;

import java.util.ArrayList;
import java.util.Random;

public class SimpleDotCom {
	ArrayList<String> ships;

	/**Sets the user inputted LOCATIONS into ships.  Should not modify contents.*/ 
	public void setLocation(ArrayList<String> locations) {
		ships = locations;
	}

	public String checkYourself(String stringGuess) {
		String result = "miss";
		int index = ships.indexOf(stringGuess);
		if (index >= 0) {
			ships.remove(index);
			if (ships.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}
		return result;
	}
}
