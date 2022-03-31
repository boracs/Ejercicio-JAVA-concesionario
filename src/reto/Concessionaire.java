package reto;

import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Concessionaire {
	
	public static void menu() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("MENU");
		sb.append("\n");
		sb.append("\n(1) Buy vehicle");
		sb.append("\n(2) Sell vehicle");
		sb.append("\n(3) Paint vehicle");
		sb.append("\n(4) Modify vehicle");
		sb.append("\n(5) Show vehicles");
		sb.append("\n(6) Check sales");
		sb.append("\n");
		System.out.println(sb.toString());
		
		int option = Console.readInt();
		
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
			
			System.out.println("\n\nCARS");
			ResultSet myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, car WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = car.registration");
			if (!myResultSetCar.next()) {                            
				System.out.println("\nNo cars to show!");
			}
			while(myResultSetCar.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("\nSeries:\t\t" + myResultSetCar.getString("brand") + " " + myResultSetCar.getString("model") + " " + myResultSetCar.getInt("year"));
				sb.append("\nRegistration:\t" + myResultSetCar.getString("registration"));
				sb.append("\nFrame number:\t" + myResultSetCar.getString("numFrame"));
				sb.append("\nColour:\t\t" + myResultSetCar.getString("colour"));
				sb.append("\nDoor number:\t" + myResultSetCar.getInt("numDoors"));
				sb.append("\nTrunk capacity:\t" + myResultSetCar.getInt("trunkCapacity"));
				System.out.println(sb.toString());
			}
			
			System.out.println("\n\nTRUCKS");
			ResultSet myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, truck WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = truck.registration");
			if (!myResultSetTruck.next()) {                            
				System.out.println("\nNo trucks to show!");
			}
			while(myResultSetTruck.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("\nSeries:\t\t" + myResultSetCar.getString("brand") + " " + myResultSetCar.getString("model") + " " + myResultSetCar.getInt("year"));
				sb.append("\nRegistration:\t" + myResultSetTruck.getString("registration"));
				sb.append("\nFrame number:\t" + myResultSetTruck.getString("numFrame"));
				sb.append("\nColour:\t\t" + myResultSetTruck.getString("colour"));
				sb.append("\nLoad:\t\t" + myResultSetTruck.getInt("load"));
				sb.append("\nMerchan. type:\t" + myResultSetTruck.getString("merchandiseType"));
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
		System.out.println("\n");
		menu();
	}

	public static void buy() {
		
		String brand;
		boolean correct = true;
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
		correct = true;
		do {
			Calendar instance = Calendar.getInstance();
	        int currentYear = instance.get(Calendar.YEAR);
			if(correct == false) {
				System.out.println("*The year must be between 1900 and " + currentYear + "!");
			}
			System.out.println("\nEnter year:");
			year = Console.readInt();
			 
			if(year > currentYear || year < 1900) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		String registration;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The registration must have 4-10 characters");
			}
			System.out.println("\nEnter registration:");
			registration = Console.readString();
			if(registration.length() > 10 || registration.length() < 4) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
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
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The seat number must be between 1 and 9!");
			}
			System.out.println("\nEnter seat number:");
			numOfSeats = Console.readInt();
			if(numOfSeats > 9 || numOfSeats < 1) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		int price;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The price must be between 0 and 1.000.000!");
			}
			System.out.println("\nEnter price:");
			price = Console.readInt();
			if(price > 1000000 || price < 0) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		int numDoors;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The door number must be between 2 and 5!");
			}
			System.out.println("\nEnter door number:");
			numDoors = Console.readInt();
			if(numDoors > 5 || numDoors < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		
		int trunkCapacity;
		correct = true;
		do {
			if(correct == false) {
				System.out.println("*The trunk capacity must be between 0 and 5000!");
			}
			System.out.println("\nEnter trunk capacity (l):");
			trunkCapacity = Console.readInt();
			if(trunkCapacity > 5000 || trunkCapacity < 0) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);

		Car myCar = new Car (brand, model, year, registration, numFrame, colour, numOfSeats, price, numDoors, trunkCapacity);
		
		
	}
	
	public static void sell() {
		showVehicles();
		System.out.println("\nEnter the registration of vehicle do you want to sell:");
	}
	
	public static void paint() {
		
	}
	
	public static void modify() {
		
	}
	
	public static void checkSales() {
		
	}
}
