package reto;

public class Car extends Vehicle {

	private int numDoors;
	private int trunkCapacity;
	
	public Car() {
		super();
	}
	
	public Car(String brand, String model, int year, String registration, String numFrame,
			String colour, int numOfSeats, int price, int numDoors, int trunkCapacity) {
		
		super(brand, model, year, registration, numFrame, colour, numOfSeats, price);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
		
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("INSERT INTO car VALUES ('" + registration.toUpperCase() + "', " + numDoors + ", " + trunkCapacity + ")");
			System.out.println("\nCar succesfully added to database!");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(myConnectionToDB != null){
                try{
                	myConnectionToDB.disconnect();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
		}
	}
	
	
	public void sell(int serieNum, String registration) {
		
		ConnectionToDB myConnectionToDB = null;

		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("DELETE FROM car WHERE carRegistration = '" + registration + "'");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(myConnectionToDB != null){
                try{
                	myConnectionToDB.disconnect();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
		}
		
		super.sell(serieNum, registration);
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
