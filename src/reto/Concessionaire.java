package reto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Concessionaire {
	
	public static void menu() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("--------- MENU ---------");
		sb.append("\n");
		sb.append("\n(1) Show vehicles");
		sb.append("\n(2) Buy vehicle");
		sb.append("\n(3) Sell vehicle");
		sb.append("\n(4) Paint vehicle");
		sb.append("\n(5) Modify vehicle data");
		sb.append("\n(6) Check sales");
		sb.append("\n(7) Export DB to XML");
		sb.append("\n(8) Import XML to DB");
		sb.append("\n\nEnter an option:");
		System.out.println(sb.toString());
		
		
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
				show();
				break;
			case 2:
				buy();
				break;
			case 3:
				sell();
				break;
			case 4:
				paint();
				break;
			case 5:
				modify(); 
				break;
			case 6:
				checkSales();
				break; 
			case 7:
				XML.exportTo();
				break; 
			case 8:
				XML.importFrom();
				break; 
		}
		
	}
	
	public static void show() {
		
		char answer = AskFor.allOrColor();
		
		String colour = null;
		if(Character.toLowerCase(answer) == 'c') {
			colour = AskFor.colour();
		}
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			
			System.out.println("\nCARS");
			ResultSet myResultSetCar;
			if(Character.toLowerCase(answer) == 'c') {
				myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, car WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = car.carRegistration AND LOWER(vehicle.colour) = '" + colour.toLowerCase() + "'");
			}else {
				myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, car WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = car.carRegistration");
			}
			if (!myResultSetCar.next()) {                            
				System.out.println("\nNo cars to show!");
			}else {
				do {
					StringBuilder sb = new StringBuilder();
					sb.append("\nSeries:\t\t" + myResultSetCar.getString("brand") + " " + myResultSetCar.getString("model") + " " + myResultSetCar.getInt("year"));
					sb.append("\nRegistration:\t" + myResultSetCar.getString("registration"));
					sb.append("\nFrame number:\t" + myResultSetCar.getString("numFrame"));
					sb.append("\nColour:\t\t" + myResultSetCar.getString("colour"));
					sb.append("\nPrice:\t\t" + myResultSetCar.getInt("price") + " �");
					sb.append("\nDoor number:\t" + myResultSetCar.getInt("numDoors"));
					sb.append("\nTrunk capacity:\t" + myResultSetCar.getInt("trunkCapacity") + " l");
					System.out.println(sb.toString());
				} while(myResultSetCar.next());
			}
			
			ResultSet myResultSetTruck;
			System.out.println("\nTRUCKS");
			if(Character.toLowerCase(answer) == 'c') {
				myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, truck WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = truck.truckRegistration AND LOWER(vehicle.colour) = '" + colour.toLowerCase() + "'");
			}else {
				myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, truck WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = truck.truckRegistration");
			}
				if (!myResultSetTruck.next()) {                            
				System.out.println("\nNo trucks to show!");
			}else {
				do {
					StringBuilder sb = new StringBuilder();
					sb.append("\nSeries:\t\t" + myResultSetTruck.getString("brand") + " " + myResultSetTruck.getString("model") + " " + myResultSetTruck.getInt("year"));
					sb.append("\nRegistration:\t" + myResultSetTruck.getString("registration"));
					sb.append("\nFrame number:\t" + myResultSetTruck.getString("numFrame"));
					sb.append("\nColour:\t\t" + myResultSetTruck.getString("colour"));
					sb.append("\nPrice:\t\t" + myResultSetTruck.getInt("price") + " �");
					sb.append("\nLoad:\t\t" + myResultSetTruck.getInt("load"));
					sb.append("\nMerchan. type:\t" + myResultSetTruck.getString("merchandiseType"));
					System.out.println(sb.toString());
				} while(myResultSetTruck.next());
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
			
			System.out.println("\n");
			menu();
		}
		
	}

	public static void buy() {
		
		char answer = AskFor.carOrTruck();
		
		String brand = AskFor.brand();
		String model = AskFor.model();
		int year = AskFor.year();
		String registration = AskFor.registration();
		String numFrame = AskFor.numFrame();
		String colour = AskFor.colour();
		int numOfSeats = AskFor.numOfSeats();
		int price = AskFor.price();
		
		if(Character.toLowerCase(answer) == 'c') {
			
			int numDoors = AskFor.numDoors();
			int trunkCapacity = AskFor.trunkCapacity();
			
			new Car (brand, model, year, registration, numFrame, colour, numOfSeats, price, numDoors, trunkCapacity);
			
		}else {
			
			int load = AskFor.load();
			char merchandiseType = AskFor.merchandiseType();
			
			new Truck (brand, model, year, registration, numFrame, colour, numOfSeats, price, load, merchandiseType);
			
		}
		
		System.out.println("\n");
		menu();
		
	}
	
	public static void sell() {
				
		int serieNum;
		String registration;
		boolean exists = true;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The registration must have 4-10 characters");
			}else if(exists == false) {
				System.out.println("*The registration does not exist!");
			}
			System.out.println("\nEnter the registration of the vehicle you want to sell:");
			registration = Console.readString();
			
			ConnectionToDB myConnectionToDB = null;
			ConnectionToDB myConnectionToDB2 = null;
			
			try {
				myConnectionToDB = new ConnectionToDB();
				ResultSet myResultSetRegistration = myConnectionToDB.myQuery("SELECT serieNum, registration FROM vehicle WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
				if (myResultSetRegistration.next()) { 
					serieNum = myResultSetRegistration.getInt("serieNum");
					registration = myResultSetRegistration.getString("registration");
					myConnectionToDB2 = new ConnectionToDB();
					ResultSet myResultSetRegistration2 = myConnectionToDB2.myQuery("SELECT carRegistration FROM car WHERE UPPER(carRegistration) = '" + registration.toUpperCase() + "'");
					if (myResultSetRegistration2.next()) { 
						new Car().sell(serieNum, registration);
					}else {
						new Truck().sell(serieNum, registration);
					}
				}else {
					exists = false;
				}
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
			
			if(registration.length() > 10 || registration.length() < 4) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false || exists == false);
		
		System.out.println("\n");
		menu();
		
	}
	
	public static void paint() {
		
		String registration;
		boolean exists = true;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The registration must have 4-10 characters");
			}else if(exists == false) {
				System.out.println("*The registration does not exist!");
			}
			System.out.println("\nEnter the registration of the vehicle you want to paint:");
			registration = Console.readString();
			
			ConnectionToDB myConnectionToDB = null;
			
			try {
				myConnectionToDB = new ConnectionToDB();
				ResultSet myResultSetRegistration = myConnectionToDB.myQuery("SELECT serieNum, registration, colour FROM vehicle WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
				if (myResultSetRegistration.next()) { 
					registration = myResultSetRegistration.getString("registration");
					
					char answer = AskFor.sameOrNewColor();
					
					if(Character.toLowerCase(answer) == 's') {
						new Car().rePaint(registration);
					}else {
						String colour = AskFor.colour();
						new Car().paint(registration, colour);
					}
					
				}else {
					exists = false;
				}
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
			
			if(registration.length() > 10 || registration.length() < 4) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false || exists == false);
		
		System.out.println("\n");
		menu();
		
	}
	
	public static void modify() {
		
		int serieNum;
		String registration;
		boolean exists = true;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The registration must have 4-10 characters");
			}else if(exists == false) {
				System.out.println("*The registration does not exist!");
			}
			System.out.println("\nEnter the registration of the vehicle you want to modify:");
			registration = Console.readString();
			
			ConnectionToDB myConnectionToDB = null;
			ConnectionToDB myConnectionToDB2 = null;
			
			try {
				myConnectionToDB = new ConnectionToDB();
				ResultSet myResultSetRegistration = myConnectionToDB.myQuery("SELECT serieNum, registration FROM vehicle WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
				if (myResultSetRegistration.next()) { 
					serieNum = myResultSetRegistration.getInt("serieNum");
					registration = myResultSetRegistration.getString("registration");
					myConnectionToDB2 = new ConnectionToDB();
					ResultSet myResultSetRegistration2 = myConnectionToDB2.myQuery("SELECT carRegistration FROM car WHERE UPPER(carRegistration) = '" + registration.toUpperCase() + "'");
					if (myResultSetRegistration2.next()) { 
						new Car().modify(serieNum, registration);
					}else {
						new Truck().modify(serieNum, registration);
					}
				}else {
					exists = false;
				}
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
			
			if(registration.length() > 10 || registration.length() < 4) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false || exists == false);
		
		System.out.println("\n");
		menu();
	}
	
	public static void checkSales() {
		
	}
	
}
