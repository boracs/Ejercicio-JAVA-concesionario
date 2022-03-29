package reto;

import java.util.Date;

public abstract class Vehicle {
	
	private int serieNum;
	private String brand;
	private String model;
	private int year;
	private String registration;
	private int numFrame;
	private String color;
	private int numOfSeats;
	private int price;
	private boolean painted;
	private boolean sold;
	private Date sellDate;
	
	public Vehicle(String brand, String model, int year, String registration, int numFrame, String color, int numOfSeats, int price,
			boolean painted, boolean sold, Date fechaVenta) {
		
		this.serieNum = serieNum;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.numFrame = numFrame;
		this.color = color;
		this.numOfSeats = numOfSeats;
		this.price = price;
		this.painted = false;
		this.sold = false;
		this.sellDate = null;
	}

	
	public int getSerieNum() {
		return this.serieNum;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}
	
	public int getYear() {
		return this.year;
	}

	public String getRegistration() {
		return this.registration;
	}

	public int getNumFrame() {
		return this.numFrame;
	}

	public String getColor() {
		return this.color;
	}

	public int getNumOfSeats() {
		return this.numOfSeats;
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isPainted() {
		return this.painted;
	}

	public boolean isSold() {
		return this.sold;
	}

	public Date getFechaVenta() {
		return this.sellDate;
	}

	public void setSerieNum(int serieNum) {
		this.serieNum = serieNum;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public void setNumFrame(int numFrame) {
		this.numFrame = numFrame;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setPainted(boolean painted) {
		this.painted = painted;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public void setFechaVenta(Date sellDate) {
		this.sellDate = sellDate;
	}

	
	
	public void paint(String color) {
		this.color = color;
		this.painted = true;
	}
	
	public void rePaint() {
		this.painted = true;
	}
	
	public void sell() {
		this.sold = true;
		this.sellDate = new Date();  //set current date
	}
	
	
}
