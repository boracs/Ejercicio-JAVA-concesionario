package reto;

import java.sql.Date;

public class Truck extends Vehicle {

	private int load;
	private char merchandiseType;
	
	public Truck(String brand, String model, int year, String registration, int numFrame, String color, int numOfSeats, int price,
			boolean painted, boolean sold, Date sellDate, int load, char merchandiseType) {
		
		super(brand, model, year, registration, numFrame, color, numOfSeats, price,
				painted, sold, sellDate);
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
