package reto;

public class Car extends Vehicle {

	private int numDoors;
	private int trunkCapacity;
	
	public Car(/*String brand, String model, int year,*/ String registration, String numFrame,
			String colour, int numOfSeats, int price, int numDoors, int trunkCapacity) {
		
		super(/*brand, model, year,*/ registration, numFrame, colour, numOfSeats, price);
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
