package reto;

public class Car extends Vehicle {
	
	public Car() {
		super();
	}
	
	public Car(String brand, String model, int year, String registration, String numFrame,
			String colour, int numOfSeats, int price, int numDoors, int trunkCapacity) {
		
		super(brand, model, year, registration, numFrame, colour, numOfSeats, price);
		
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

	public void paint(String registration, String colour) {
		super.paint(registration, colour);
	}
	
}
