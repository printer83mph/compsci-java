package classes;

public class ParkingGarage {
	
	private Car[] lot;
	
	public ParkingGarage() {
		lot = new Car[30];
	}
	
	public ParkingGarage(int size) {
		lot = new Car[size];
	}
	
	public void park(Car car) {
		if (firstOpenSpot() == -1) {
			System.out.println("No more space, move along!");
		} else {
			lot[firstOpenSpot()] = car;
		}
	}
	
	public void park(Car car, int spot) {
		if (lot.length <= spot) {System.out.println("Sorry, we don't have that many spots!"); return;}
		if (lot[spot] == null) {lot[spot] = car;}
		else {System.out.println("Sorry, that spot is taken!");}
	}
	
	public int spotsLeft() {
		int out = 0;
		for (int i = 0; i < lot.length; i++) {
			if (lot[i] != null) {out++;}
		}
		return out;
	}
	
	public int spotsTaken() {
		return lot.length - spotsLeft();
	}
	
	public int getSize() {
		return lot.length;
	}
	
	public int firstOpenSpot() {
		for (int i = 0; i < lot.length; i++) {
			if (lot[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public void printDetails() {
		for (int i = 0; i < lot.length; i++) {
			if (lot[i] == null) {
				System.out.println("Empty spot");
			} else {System.out.println(lot[i].getDetails());}
		}
	}
	
}
