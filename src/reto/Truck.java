package reto;

public class Truck extends Vehicle {

	private int load;
	private char merchandiseType;
	
	public Truck(String brand, String model, int year, String registration, String numFrame,
			String colour, int numOfSeats, int price, int load, char merchandiseType) {
		
		super(brand, model, year, registration, numFrame, colour, numOfSeats, price);
		this.load = load;
		this.merchandiseType = merchandiseType;
	}

	
	public int getLoad() {
		return this.load;
	}

	public char getMerchandiseType() {
		return this.merchandiseType;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public void setMerchandiseType(char merchandiseType) {
		this.merchandiseType = merchandiseType;
	}
	
	
}
