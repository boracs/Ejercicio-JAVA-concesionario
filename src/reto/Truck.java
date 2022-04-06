package reto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Truck extends Vehicle {
	
	public Truck() {
		
	}
	
	public Truck(String brand, String model, int year, String registration, String numFrame,
			String colour, int numOfSeats, int price, int load, char merchandiseType) {
		
		super(brand, model, year, registration, numFrame, colour, numOfSeats, price);
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("INSERT INTO truck VALUES ('" + registration.toUpperCase() + "', " + load + ", '" + Character.toUpperCase(merchandiseType) + "')");
			System.out.println("\nTruck succesfully added to database!");
			
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
			myConnectionToDB.myExeQuery("DELETE FROM truck WHERE truckRegistration = '" + registration + "'");
			
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
	
	public void modifyMenu(int serieNum, String registration) {
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			ResultSet myResultSetTruck;
			myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, truck WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = truck.truckRegistration AND UPPER(truck.truckRegistration) = '" + registration.toUpperCase() + "'");
			if (myResultSetTruck.next()) {
			StringBuilder sb = new StringBuilder();
			sb.append("\n==> MODIFY TRUCK DATA");
			sb.append("\n");
			sb.append("\n(1) Brand:\t\t" + myResultSetTruck.getString("brand"));
			sb.append("\n(2) Model:\t\t" + myResultSetTruck.getString("model"));
			sb.append("\n(3) Year:\t\t" + myResultSetTruck.getString("year"));
			sb.append("\n(4) Frame number:\t" + myResultSetTruck.getString("numFrame"));
			sb.append("\n(5) Price:\t\t" + myResultSetTruck.getInt("price") + " €");
			sb.append("\n(6) Load:\t\t" + myResultSetTruck.getInt("load") + " kg");
			sb.append("\n(7) Merchan. type:\t" + myResultSetTruck.getString("merchandiseType"));
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
				modifyBrand(serieNum, registration);
				break;
			case 2:
				modifyModel(serieNum, registration);
				break;
			case 3:
				modifyYear(serieNum, registration);
				break;
			case 4:
				modifyNumFrame(registration);
				break;
			case 5:
				modifyPrice(registration);
				break;
			case 6:
				modifyLoad(registration);
				break; 
			case 7:
				modifyMerchandiseType(registration);
				break; 
 
		}
	}
	
	public void modifyLoad(String registration) {
		
		int load = AskFor.load();
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE truck SET truck.load = " + load + " WHERE truckRegistration = '" + registration.toUpperCase() + "'");
			
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
	
	public void modifyMerchandiseType(String registration) {
		
		char merchandiseType = AskFor.merchandiseType();
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE truck SET merchandiseType = '" + Character.toUpperCase(merchandiseType) + "' WHERE UPPER(truck.truckRegistration) = '" + registration.toUpperCase() + "'");
			
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

}
