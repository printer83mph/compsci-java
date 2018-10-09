package parkingGarage;

public class Car {

	private String color;
	private String owner;
	private String licensePlate;
	private int height;
	
	public String getColor() {return color;}
	public void setColor(String newColor) {color = newColor;}
	
	public String getOwner() {return owner;}
	public void setOwner(String newOwner) {owner = newOwner;}
	
	public String getLicensePlate() {return licensePlate;}
	public void setLicensePlate(String newLicensePlate) {licensePlate = newLicensePlate;}
	
	public int getHeight() {return height;}
	public void setHeight(int newHeight) {height = newHeight;}
	
	public Car(String c, String o, String l, int h) {
		color = c;
		owner = o;
		licensePlate = l;
		height = h;
	}
	
	public String getDetails() {
		return owner + "'s " + color + " car, license plate " + licensePlate + " and " + height + " feet tall";
	}
	
}
