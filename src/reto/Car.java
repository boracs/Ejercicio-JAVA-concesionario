package reto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

public class Car extends Vehicle {
	
	Connection conn = null;
	
	final String url = "jdbc:mysql:sql/";
	final String dbName = "reto";
	final String driver = "com.mysql.jdbc.Driver";
	final String userName = "root";
	final String password = "root";

	private int numDoors;
	private int trunkCapacity;
	
	public Car(int serieNum, String brand, String model, int year, String registration, int numFrame, String color, int numOfSeats, int price,
			boolean painted, boolean sold, Date fechaVenta, int numDoors, int trunkCapacity) {
		
		super(serieNum, brand, model, year, registration, numFrame, color, numOfSeats, price,
				painted, sold, fechaVenta);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
		
	}
	

}
