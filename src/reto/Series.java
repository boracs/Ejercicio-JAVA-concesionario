package reto;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public int getSerieNum() {
		return this.serieNum;
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
	
	public void modifyBrand(int serieNum, String registration) {
		
		String brand = AskFor.brand();
		ConnectionToDB myConnectionToDB = null;
		String model = null;
		int year = 0;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT brand, model, year FROM series WHERE serieNum = " + serieNum);
			myResultSet.next();
			model = myResultSet.getString("model");
			year = myResultSet.getInt("year");
			
			myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM vehicle WHERE serieNum = " + serieNum);
			int rows = 0;
			while (myResultSet.next()) {
			    rows++;
			}
			
			if(rows == 1) {
				//si solo hay un vehiculo de esa serie
				myConnectionToDB.myExeQuery("UPDATE series SET brand = '" + brand + "' WHERE serieNum = " + serieNum);
			}else {
				//si hay varios vehiculos de la serie
				char answer = AskFor.allSeriesOrThisVehicle();
				
				if(Character.toLowerCase(answer) == 'a') {
					//si queremos aplicar el cambio a todos los vehiculos de la serie
					myConnectionToDB.myExeQuery("UPDATE series SET brand = '" + brand + "' WHERE serieNum = " + serieNum);
				}else {
					//creamos una nueva serie si solo es este vehiculo el que cambia
					myConnectionToDB.myExeQuery("INSERT INTO series (brand, model, year) VALUES ('" + brand + "', '" + model + "', " + year + ")");

				}
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
			
			deleteEqualSeries(brand, model, year);
		}
	}
	
	public void modifyModel(int serieNum, String registration) {
		
		String model = AskFor.model();
		ConnectionToDB myConnectionToDB = null;
		String brand = null;
		int year = 0;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT brand, model, year FROM series WHERE serieNum = " + serieNum);
			myResultSet.next();
			brand = myResultSet.getString("brand");
			year = myResultSet.getInt("year");
			
			myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM vehicle WHERE serieNum = " + serieNum);
			int rows = 0;
			while (myResultSet.next()) {
				rows++;
			}
			
			if(rows == 1) {
				//si solo hay un vehiculo de esa serie
				myConnectionToDB.myExeQuery("UPDATE series SET model = '" + model + "' WHERE serieNum = " + serieNum);
			}else {
				//si hay varios vehiculos de la serie
				char answer = AskFor.allSeriesOrThisVehicle();
				
				if(Character.toLowerCase(answer) == 'a') {
					//si queremos aplicar el cambio a todos los vehiculos de la serie
					myConnectionToDB.myExeQuery("UPDATE series SET model = '" + model + "' WHERE serieNum = " + serieNum);
				}else {
					//creamos una nueva serie si solo es este vehiculo el que cambia
					myConnectionToDB.myExeQuery("INSERT INTO series (brand, model, year) VALUES ('" + brand + "', '" + model + "', " + year + ")");
					
				}
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
			
			deleteEqualSeries(brand, model, year);
		}
	}
	
	public void modifyYear(int serieNum, String registration) {
		
		int year = AskFor.year();
		ConnectionToDB myConnectionToDB = null;
		String brand = null;
		String model = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT brand, model, year FROM series WHERE serieNum = " + serieNum);
			myResultSet.next();
			brand = myResultSet.getString("brand");
			model = myResultSet.getString("model");
			
			myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM vehicle WHERE serieNum = " + serieNum);
			int rows = 0;
			while (myResultSet.next()) {
				rows++;
			}
			
			if(rows == 1) {
				//si solo hay un vehiculo de esa serie
				myConnectionToDB.myExeQuery("UPDATE series SET year = " + year + " WHERE serieNum = " + serieNum);
			}else {
				//si hay varios vehiculos de la serie
				char answer = AskFor.allSeriesOrThisVehicle();
				
				if(Character.toLowerCase(answer) == 'a') {
					//si queremos aplicar el cambio a todos los vehiculos de la serie
					myConnectionToDB.myExeQuery("UPDATE series SET year = " + year + " WHERE serieNum = " + serieNum);
				}else {
					//creamos una nueva serie si solo es este vehiculo el que cambia
					myConnectionToDB.myExeQuery("INSERT INTO series (brand, model, year) VALUES ('" + brand + "', '" + model + "', " + year + ")");
					
				}
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
			
			deleteEqualSeries(brand, model, year);
		}
	}

	public void deleteEqualSeries(String brand, String model, int year) {
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			//comprobamos si ahora hay dos series iguales
			ResultSet myResultSet = myConnectionToDB.myQuery("SELECT serieNum FROM series WHERE brand = '" + brand + "' AND model = '" + model + "' AND year = " + year);
			myResultSet.next();
			// aunque haya más de uno, el vehiculo que estabamos modificando va a coger el valor serieNum de la primera vez que sale la serie
			this.serieNum = myResultSet.getInt("serieNum");
			
			// para los siguientes resultados de vehiculos que pertenecen series iguales, asignaremos el primer serieNum encontrado, y borraremos la serie que esta repetida
			while (myResultSet.next()) {
				ConnectionToDB myConnectionToDB2 = null;
				
				try {
					myConnectionToDB2 = new ConnectionToDB();
					myConnectionToDB2.myExeQuery("UPDATE vehicle SET serieNum = " + this.serieNum + " WHERE serieNum = " + myResultSet.getInt("serieNum"));
					myConnectionToDB2.myExeQuery("DELETE FROM series WHERE serieNum = " + myResultSet.getInt("serieNum"));
					
				} catch (SQLException e) {
					e.printStackTrace();
					
				} finally {
					if(myConnectionToDB2 != null){
						try{
							myConnectionToDB2.disconnect();
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				}
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
	}

}
