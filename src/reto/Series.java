package reto;

import java.sql.ResultSet;

public class Series {
	
	private int serieNum;
	private String brand;
	private String model;
	private int year;
	
	public Series(String brand, String model, int year) {
		
		this.brand = brand;
		this.model = model;
		this.year = year;
		
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM series WHERE brand = '" + brand + "' AND model = '" + model + "' AND year = '" + year + "'");
			if (!myResultSet.next()) {                            
				myConnectionToDB.myExeQuery("INSERT INTO series (brand, model, year) VALUES ('" + brand + "', '" + model + "', '" + year + "')");
				myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM series WHERE brand = '" + brand + "' AND model = '" + model + "' AND year = '" + year + "'");
			}
			this.serieNum = myResultSet.getInt("serieNum");
			
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
	
	public String getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}
	
	public int getYear() {
		return this.year;
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
}
