package reto;

import java.sql.ResultSet;

public abstract class Series {
	
	private int serieNum;
	
	public Series() {
		
	}
	
	public Series(String brand, String model, int year) {
		
		ConnectionToDB myConnectionToDB = null;

		try {
			myConnectionToDB = new ConnectionToDB();
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM series WHERE LOWER(brand) = '" + brand.toLowerCase() + "' AND LOWER(model) = '" + model.toLowerCase() + "' AND year = " + year);
			if (!myResultSet.next()) {                            
				myConnectionToDB.myExeQuery("INSERT INTO series (brand, model, year) VALUES ('" + brand + "', '" + model + "', " + year + ")");
				myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM series WHERE brand = '" + brand + "' AND model = '" + model + "' AND year = " + year);
				myResultSet.next();
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
	
	public void sell(int serieNum) {
		
		ConnectionToDB myConnectionToDB = null;

		try {
			myConnectionToDB = new ConnectionToDB();
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM vehicle WHERE serieNum = " + serieNum);
			int rows = 0;
			while (myResultSet.next()) {
			    rows++;
			}
		    
			if(rows < 1) {
				myConnectionToDB.myExeQuery("DELETE FROM series WHERE serieNum = " + serieNum);
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
		
		System.out.println("\nVehicle succesfully removed from database!");
	}
	
	public int getSerieNum() {
		return this.serieNum;
	}
	
}
