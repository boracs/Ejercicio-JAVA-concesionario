package reto;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public void modify(int serieNum, String registration) {
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			ResultSet myResultSetCar;
			myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, car WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = car.carRegistration AND UPPER(car.carRegistration) = '" + registration.toUpperCase() + "'");
			if (myResultSetCar.next()) {
			StringBuilder sb = new StringBuilder();
			sb.append("\n==> MODIFY CAR DATA");
			sb.append("\n");
			sb.append("\n(1) Brand:\t\t" + myResultSetCar.getString("brand"));
			sb.append("\n(2) Model:\t\t" + myResultSetCar.getString("model"));
			sb.append("\n(3) Year:\t\t" + myResultSetCar.getString("year"));
			sb.append("\n(4) Registration:\t" + myResultSetCar.getString("registration"));
			sb.append("\n(5) Frame number:\t" + myResultSetCar.getString("numFrame"));
			sb.append("\n(6) Colour:\t\t" + myResultSetCar.getString("colour"));
			sb.append("\n(7) Price:\t\t" + myResultSetCar.getInt("price") + " �");
			sb.append("\n(8) Door number:\t" + myResultSetCar.getInt("numDoors"));
			sb.append("\n(9) Trunk capacity:\t" + myResultSetCar.getInt("trunkCapacity") + " l");
			sb.append("\n\nEnter an option:");
			System.out.println(sb.toString());
			}
		} catch (SQLException e) {
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
		
		int option;
		String optionString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The options are from 1 to 8!\n\nEnter an option:");
			}
			optionString = Console.readString();
			
			while(!optionString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter an option:");
				optionString = Console.readString();
			}
			option = Integer.parseInt(optionString);
			if(option < 1 || option > 8) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		switch(option) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break; 
			case 7:
				break; 
			case 8:
				break; 
		}
	}
	
}
