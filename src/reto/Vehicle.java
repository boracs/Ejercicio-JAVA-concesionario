package reto;

import java.sql.Date;

public abstract class Vehicle extends Series {
	
	private String registration;
	private int numFrame;
	private String color;
	private int numOfSeats;
	private int price;
	private boolean painted;
	private boolean sold;
	private Date fechaVenta;
	
	public Vehicle(int serieNum, String brand, String model, int year, String registration, int numFrame, String color, int numOfSeats, int price,
			boolean painted, boolean sold, Date fechaVenta) {
		
		super(serieNum, brand, model, year);
		this.registration = registration;
		this.numFrame = numFrame;
		this.color = color;
		this.numOfSeats = numOfSeats;
		this.price = price;
		this.painted = painted;
		this.sold = sold;
		this.fechaVenta = fechaVenta;
		
	}
	
	
}
