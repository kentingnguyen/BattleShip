package guibattleship;

public class Ship {

	private String _type = "";
	private int _life = 0;
	private int _size = 0;
	private int _id;
	
Ship(int id, String type, int n) {
	_id = id;
	_type = type;
	_life = n;
	_size = n;
	}

int id() {
	return _id;
}

void hit() {
	_life--;
}

String type() {
	return _type;
}

int size() {
	return _size;
}


boolean checkSink() {
return (_life == 0); 
}

}
