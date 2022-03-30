package reto;

import java.util.Date;

public abstract class Vehicle /*extends Series*/{
	
	private int serieNum;
	private String registration;
	private String numFrame;
	private String colour;
	private int numOfSeats;
	private int price;
	private boolean painted;
	private boolean sold;
	private Date sellDate;
	
	public Vehicle(/*String brand, String model, int year,*/ String registration, String numFrame, String colour, int numOfSeats, int price) {
		
		/*super(brand, model, year);*/
		this.serieNum = -1;
		this.numFrame = numFrame;
		this.colour = colour;
		this.numOfSeats = numOfSeats; 
		this.price = price;
		this.painted = false;
		this.sold = false;
		this.sellDate = null;
		
		//aquí probamos el insert into
	}

	
	public int getSerieNum() {
		return this.serieNum;
	}
	
	public String getRegistration() {
		return this.registration;
	}
	
	public String getNumFrame() {
		return this.numFrame;
	}
	
	public String getColour() {
		return this.colour;
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

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public void setNumFrame(String numFrame) {
		this.numFrame = numFrame;
	}

	public void setColour(String colour) {
		this.colour = colour;
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

	
	
	public void paint(String colour) {
		this.colour = colour;
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
