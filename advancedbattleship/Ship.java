package advancedbattleship;

public class Ship {

	private String id = "";
	private int life = 0;
	private int size = 0;
	
Ship(String type, int n) {
	id = type;
	life = n;
	size = n;
	}

void hit() {
	life--;
}

String type() {
	return id;
}

int size() {
	return size;
}


boolean checkSink() {
return (life == 0); 
}

}
