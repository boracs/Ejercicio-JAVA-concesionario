package reto;

public abstract class Series {
	
	private int serieNum;
	private String brand;
	private String model;
	private int year;
	
	public Series(int serieNum, String brand, String model, int year) {
		
		this.serieNum = serieNum;
		this.brand = brand;
		this.model = model;
		this.year = year;
		
	}
	
	
}
