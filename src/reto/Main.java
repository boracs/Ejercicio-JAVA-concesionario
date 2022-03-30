package reto;

import java.sql.*;

public class Main {
	
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
	
	public static void buy() {
		
	}
	
	public static void sell() {
		showVehicles();
		System.out.println("Which vehicle do you want to sell?\n");
	}
	
	public static void paint() {
		
	}
	
	public static void modify() {
		
	}
	
	public static void showVehicles() {
		
		try {
			ConnectionToDB myConnectionToDB = new ConnectionToDB();
			
			System.out.println("CARS");
			ResultSet myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM vehicle, car WHERE vehicle.registration = car.registration");
			while(myResultSetCar.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("\nRegistration:\t" + myResultSetCar.getString("registration"));
				sb.append("\nFrame number:\t" + myResultSetCar.getString("numFrame"));
				sb.append("\nColour:\t\t" + myResultSetCar.getString("colour"));
				sb.append("\nDoors number:\t" + myResultSetCar.getInt("numDoors"));
				sb.append("\nTrunk capacity:\t" + myResultSetCar.getInt("trunkCapacity"));
				System.out.println(sb.toString());
			}
			
			System.out.println("\n\nTRUCKS");
			ResultSet myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM vehicle, truck WHERE vehicle.registration = truck.registration");
			while(myResultSetTruck.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("\nRegistration:\t" + myResultSetTruck.getString("registration"));
				sb.append("\nFrame number:\t" + myResultSetTruck.getString("numFrame"));
				sb.append("\nColour:\t\t" + myResultSetTruck.getString("colour"));
				sb.append("\nLoad:\t\t" + myResultSetTruck.getInt("load"));
				sb.append("\nMerchandise t.:\t" + myResultSetTruck.getString("merchandiseType"));
				System.out.println(sb.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void checkSales() {
		
	}
	
	public static void main(String[] args) {
		
		showVehicles();
	}

}
