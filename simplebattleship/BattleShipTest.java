
package simplebattleship;
import java.util.ArrayList;

public class BattleShipTest {
public static void main(String[] args) {
	SimpleDotCom dot = new SimpleDotCom();
	
	ArrayList<String> locations = new ArrayList<String>();
	locations.add("2");
	dot.setLocation(locations);
	String userGuess = "2";
	String result = dot.checkYourself(userGuess);
	String testresult = "failed";
	if (result.equals("hit")) {
		testresult = "passed";
	}
	System.out.println(testresult);
	}
}