package reto;

import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Concessionaire {
	
	public static void menu() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("------ MENU ------");
		sb.append("\n");
		sb.append("\n(1) Buy vehicle");
		sb.append("\n(2) Sell vehicle");
		sb.append("\n(3) Paint vehicle");
		sb.append("\n(4) Modify vehicle");
		sb.append("\n(5) Show vehicles");
		sb.append("\n(6) Check sales");
		sb.append("\n\nEnter an option:");
		System.out.println(sb.toString());
		
		
		int option;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The options are from 1 to 6!\n\nEnter an option:");
			}
			option = Console.readInt();
			if(option < 1 || option > 6) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		switch(option) {
			case 1:
				buy();
				break;
			case 2:
				sell();
				break;
			case 3:
				paint();
				break;
			case 4:
				modify();
				break;
			case 5:
				showVehicles(); 
				break;
			case 6:
				checkSales();
				break; 

		}
	}
	
	public static void showVehicles() {
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			
			System.out.println("\nCARS");
			ResultSet myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, car WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = car.carRegistration");
			if (!myResultSetCar.next()) {                            
				System.out.println("\nNo cars to show!");
			}else {
				do {
					StringBuilder sb = new StringBuilder();
					sb.append("\nSeries:\t\t" + myResultSetCar.getString("brand") + " " + myResultSetCar.getString("model") + " " + myResultSetCar.getInt("year"));
					sb.append("\nRegistration:\t" + myResultSetCar.getString("registration"));
					sb.append("\nFrame number:\t" + myResultSetCar.getString("numFrame"));
					sb.append("\nColour:\t\t" + myResultSetCar.getString("colour"));
					sb.append("\nPrice:\t\t" + myResultSetCar.getInt("price") + " €");
					sb.append("\nDoor number:\t" + myResultSetCar.getInt("numDoors"));
					sb.append("\nTrunk capacity:\t" + myResultSetCar.getInt("trunkCapacity") + " l");
					System.out.println(sb.toString());
				} while(myResultSetCar.next());
			}
			
			System.out.println("\nTRUCKS");
			ResultSet myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, truck WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = truck.truckRegistration");
			if (!myResultSetTruck.next()) {                            
				System.out.println("\nNo trucks to show!");
			}else {
				do {
					StringBuilder sb = new StringBuilder();
					sb.append("\nSeries:\t\t" + myResultSetTruck.getString("brand") + " " + myResultSetTruck.getString("model") + " " + myResultSetTruck.getInt("year"));
					sb.append("\nRegistration:\t" + myResultSetTruck.getString("registration"));
					sb.append("\nFrame number:\t" + myResultSetTruck.getString("numFrame"));
					sb.append("\nColour:\t\t" + myResultSetTruck.getString("colour"));
					sb.append("\nPrice:\t\t" + myResultSetTruck.getInt("price") + " €");
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
		
		char answer;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter 'c' for car or 't' for truck!");
			}
			System.out.println("\nWhich kind of vehicle are you buying? (Enter 'c' for car or 't' for truck)");
			answer = Console.readChar();
			if(Character.toLowerCase(answer) == 'c' || Character.toLowerCase(answer) == 't') {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);	
		
		
		String brand;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The brand must have 2-45 characters!");
			}
			System.out.println("\nEnter brand:");
			brand = Console.readString();
			if(brand.length() > 45 || brand.length() < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);

		
		String model;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The model must have 2-45 characters!");
			}
			System.out.println("\nEnter model:");
			model = Console.readString();
			if(model.length() > 45 || model.length() < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		int year;
		String yearString;
		correct = true;
		do {
			Calendar instance = Calendar.getInstance();
	        int currentYear = instance.get(Calendar.YEAR);
			if(correct == false) {
				System.out.println("*The year must be between 1900 and " + currentYear + "!");
			}
			System.out.println("\nEnter year:");
			yearString = Console.readString();
			
			while(!yearString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter year:");
				yearString = Console.readString();
			}
			year = Integer.parseInt(yearString);
			 
			if(year > currentYear || year < 1900) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		String registration;
		boolean exists = false;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The registration must have 4-10 characters");
			}else if(exists == true) {
				System.out.println("*The registration allready exists!");
			}
			System.out.println("\nEnter registration:");
			registration = Console.readString();
			
			ConnectionToDB myConnectionToDB = null;
			
			try {
				myConnectionToDB = new ConnectionToDB();
				ResultSet myResultSetRegistration = myConnectionToDB.myQuery("SELECT numFrame FROM vehicle WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
				if (myResultSetRegistration.next()) { 
					exists = true;
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
		} while (correct == false || exists == true);
		
		
		String numFrame;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The frame number must have 17 characters!");
			}
			System.out.println("\nEnter frame number:");
			numFrame = Console.readString();
			if(numFrame.length() != 17) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		String colour;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The colour must have 3-45 characters!");
			}
			System.out.println("\nEnter colour:");
			colour = Console.readString();
			if(colour.length() > 45 || colour.length() < 3) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		int numOfSeats;
		String numOfSeatsString;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The seat number must be between 1 and 9!");
			}
			System.out.println("\nEnter seat number:");
			numOfSeatsString = Console.readString();
			
			while(!numOfSeatsString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter seat number:");
				numOfSeatsString = Console.readString();
			}
			numOfSeats = Integer.parseInt(numOfSeatsString);
			
			if(numOfSeats > 9 || numOfSeats < 1) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		int price;
		String priceString;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The price must be between 0 and 3.000.000!");
			}
			System.out.println("\nEnter price:");
			priceString = Console.readString();
			
			while(!priceString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter price:");
				priceString = Console.readString();
			}
			price = Integer.parseInt(priceString);

			if(price > 3000000 || price < 0) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);	
		
		
		if(Character.toLowerCase(answer) == 'c') {
			
			
			int numDoors;
			String numDoorsString;
			correct = true;
			do {
				if(correct == false) {
					System.out.println("*The door number must be between 2 and 5!");
				}
				System.out.println("\nEnter door number:");
				numDoorsString = Console.readString();
				
				while(!numDoorsString.matches("[0-9]+")){
					System.out.println("*Only numbers!");
					System.out.println("\nEnter door number:");
					numDoorsString = Console.readString();
				}
				numDoors = Integer.parseInt(numDoorsString);
				
				if(numDoors > 5 || numDoors < 2) {
					correct = false;
				}else {
					correct = true;
				}
			} while (correct == false);
			
			
			int trunkCapacity;
			String trunkCapacityString;
			correct = true;
			do {
				if(correct == false) {
					System.out.println("*The trunk capacity must be between 0 and 5000!");
				}
				System.out.println("\nEnter trunk capacity (l):");
				trunkCapacityString = Console.readString();
				
				while(!trunkCapacityString.matches("[0-9]+")){
					System.out.println("*Only numbers!");
					System.out.println("\nEnter trunk capacity (l):");
					trunkCapacityString = Console.readString();
				}
				trunkCapacity = Integer.parseInt(trunkCapacityString);
				
				if(trunkCapacity > 5000 || trunkCapacity < 0) {
					correct = false;
				}else {
					correct = true;
				}
			} while (correct == false);

			
			Car myCar = new Car (brand, model, year, registration, numFrame, colour, numOfSeats, price, numDoors, trunkCapacity);
			
			
		}else {
			
			
			int load;
			String loadString;
			correct = true;
			do {
				if(correct == false) {
					System.out.println("*The load must be between 0 and 40000!");
				}
				System.out.println("\nEnter load (kg):");
				loadString = Console.readString();
				
				while(!loadString.matches("[0-9]+")){
					System.out.println("*Only numbers!");
					System.out.println("\nEnter load (kg):");
					loadString = Console.readString();
				}
				load = Integer.parseInt(loadString);

				if(load > 40000 || load < 0) {
					correct = false;
				}else {
					correct = true;
				}
			} while (correct == false);
			
			
			char merchandiseType;
			correct = true;
			do {
				if(correct == false) {
					System.out.println("*Enter 'G' for general, 'A' for arid or 'D' for dangerous!");
				}
				System.out.println("\nEnter merchandise type: ('G' for general, 'A' for arid or 'D' for dangerous)");
				merchandiseType = Console.readChar();
				if(Character.toUpperCase(merchandiseType) == 'G' || Character.toUpperCase(merchandiseType) == 'A' || Character.toUpperCase(merchandiseType) == 'D') {
					correct = true;
				}else {
					correct = false;
				}
			} while (correct == false);
			
			
			Truck myTruck = new Truck (brand, model, year, registration, numFrame, colour, numOfSeats, price, load, merchandiseType);
			
			
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
			System.out.println("\nEnter the registration of vehicle do you want to sell:");
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
						Car myCar = new Car();
						myCar.sell(serieNum, registration);
					}else {
						Truck myTruck = new Truck();
						myTruck.sell(serieNum, registration);
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
		
	}
	
	public static void modify() {
		
	}
	
	public static void checkSales() {
		
	}
}
