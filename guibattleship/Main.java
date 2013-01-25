package guibattleship;

public class Main {
	static boolean wantPlayGame = true;

	public static void main(String[] args) {

		GuiMain menu = new GuiMain();

	}

	/**A static method that takes an alphanumeric coordinate and returns a row, column number.*/	
	public static int[] convertToNumer(String s) {
	int[] row = new int[2];
	row[0] = convertToNumer(s.charAt(0));
	row[1] = Character.getNumericValue(s.charAt(1)) - 1;
	return row;
	}
		
		
	public static void wait (int n){
        long t0,t1;
        t0=System.currentTimeMillis();
        do{
            t1=System.currentTimeMillis();
        }
        while (t1-t0<(n*1000));
}
	
	/**A static method that takes NUM and returns an alapbetic coordinate.*/
	public static int convertToNumer(char c) {
		switch (c) {
		case 'A':
			return 0;

		case 'B':
			return 1;

		case 'C':
			return 2;

		case 'D':
			return 3;

		case 'E':
			return 4;

		case 'F':
			return 5;

		case 'G':
			return 6;

		default:
			return 0;
		}
	}

	/**A static method that takes NUM and returns an alapbetic coordinate.*/
	public static String convertToAlpha(int num) {
		switch (num) {
		case 0:
			return "A";

		case 1:
			return "B";

		case 2:
			return "C";

		case 3:
			return "D";

		case 4:
			return "E";

		case 5:
			return "F";

		case 6:
			return "G";

		default:
			return "A";
		}
	}

	static void stopGame() {
		wantPlayGame = false;
	}

}


