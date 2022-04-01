package reto;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Vehicle extends Series{
	
	private int serieNum;
	private String registration;
	private String numFrame;
	private String colour;
	private int numOfSeats;
	private int price;
	private int painted;
	private int sold;
	private Date buyDate;
	private Date sellDate;
	
	
	public Vehicle(String brand, String model, int year, String registration,
			String numFrame, String colour, int numOfSeats, int price) {
		
		super(brand, model, year);
		this.serieNum = super.getSerieNum();
		this.numFrame = numFrame;
		this.colour = colour;
		this.numOfSeats = numOfSeats; 
		this.price = price;
		this.painted = 0;
		this.sold = 0;
		this.buyDate = new Date();
		this.sellDate = new Date(0);
		
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("INSERT INTO vehicle VALUES (" + super.getSerieNum() + ", '" + registration + "', '" + numFrame + "', '" + colour + "', " + numOfSeats + ", " + price + ", 0, 0, CURDATE(), '0000-00-00')");
			
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

	public int isPainted() {
		return this.painted;
	}

	public int isSold() {
		return this.sold;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}
	
	public Date getSellDate() {
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

	public void setPainted(int painted) {
		this.painted = painted;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public void setBuyDate(Date buyDate) {
		this.sellDate = buyDate;
	}
	
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	
	
	public void paint(String colour) {
		this.colour = colour;
		this.painted = 1;
	}
	
	public void rePaint() {
		this.painted = 1;
	}
	
	public void sell() {
		this.sold = 1;
		this.sellDate = new Date();
	}
	
	
}
