package reto;

import java.sql.Date;

public class Truck extends Vehicle {

	private int load;
	private char merchandiseType;
	
	public Truck(int serieNum, String brand, String model, int year, String registration, int numFrame, String color, int numOfSeats, int price,
			boolean painted, boolean sold, Date fechaVenta, int load, char merchandiseType) {
		
		super(serieNum, brand, model, year, registration, numFrame, color, numOfSeats, price,
				painted, sold, fechaVenta);
		this.load = load;
		this.merchandiseType = merchandiseType;
		
	}
	

}
