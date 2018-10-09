package classes;

public class ParkingGarageTest {
	public static void main(String[] args) {
		ParkingGarage garage = new ParkingGarage(15);
		for (int i = 0; i < 17; i += 2) {
			garage.park(new Car("White", "Guido Mista", "GMISTA4444", 4), i);
		}
		garage.printDetails();
	}
}
