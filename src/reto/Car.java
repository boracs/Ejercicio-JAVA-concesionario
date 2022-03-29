package reto;

import java.util.Date;

public class Car extends Vehicle {

	private int numDoors;
	private int trunkCapacity;
	
	public Car(String brand, String model, int year, String registration, int numFrame, String color, int numOfSeats, int price,
			boolean painted, boolean sold, Date fechaVenta, int numDoors, int trunkCapacity) {
		
		super(brand, model, year, registration, numFrame, color, numOfSeats, price,
				painted, sold, fechaVenta);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	
	public int getNumDoors() {
		return this.numDoors;
	}

	public int getTrunkCapacity() {
		return this.trunkCapacity;
	}

	public void setNumDoors(int numDoors) {
		this.numDoors = numDoors;
	}

	public void setTrunkCapacity(int trunkCapacity) {
		this.trunkCapacity = trunkCapacity;
	}
	
	
}
