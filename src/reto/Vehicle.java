package reto;

public abstract class Vehicle extends Series{
	
	public Vehicle() {
		super();
	}
	
	public Vehicle(String brand, String model, int year, String registration,
			String numFrame, String colour, int numOfSeats, int price) {
		
		super(brand, model, year);
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("INSERT INTO vehicle VALUES (" + super.getSerieNum() + ", '" + registration + "', '" + numFrame + "', '" + colour.toLowerCase() + "', " + numOfSeats + ", " + price + ", 0, CURDATE())");
			
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
	
	public void sell(int serieNum, String registration) {
		
		ConnectionToDB myConnectionToDB = null;

		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("DELETE FROM vehicle WHERE registration = '" + registration + "'");
			
			
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
		
		super.sell(serieNum);
	}

	public void paint(String registration, String colour) {
		
		ConnectionToDB myConnectionToDB = null;

		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE vehicle SET colour = '" + colour + "', painted = 1 WHERE registration = '" + registration + "'");
			
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
		
		System.out.println("\nVehicle succesfully painted " + colour + "!");
	}
	
	public void rePaint(String registration) {
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE vehicle SET painted = 1 WHERE registration = '" + registration + "'");
			
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
		
		System.out.println("\nVehicle succesfully repainted!");
	}

	public void modifyRegistration(String registration, String newRegistration) {
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE vehicle SET registration = " + newRegistration.toUpperCase() + " WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
			
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
		
	public void modifyNumFrame(String registration) {
		
		String numFrame = AskFor.numFrame();
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE vehicle SET numFrame = " + numFrame + " WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
			
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
	
	public void modifyPrice(String registration) {
		
		int price = AskFor.price();
		ConnectionToDB myConnectionToDB = null;
		
		try {
			myConnectionToDB = new ConnectionToDB();
			myConnectionToDB.myExeQuery("UPDATE vehicle SET price = " + price + " WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
			
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

}
