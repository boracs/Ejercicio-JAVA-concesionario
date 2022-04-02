package reto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConnectionToDB {
	
	private Connection myConnection;
	private final String path = "jdbc:mysql://localhost:3306/reto_grupo_7";
	private final String user = "root";
	private final String password = "";
	
	@SuppressWarnings("finally")
	public ResultSet myQuery(String myQuery) {
		
		ResultSet myResultSet = null;
		
		try {
			this.myConnection = DriverManager.getConnection(path, user, password);
			Statement myStatement = this.myConnection.createStatement();
			myResultSet = myStatement.executeQuery(myQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			return myResultSet;
			
		}
	}
	
	
	public void myExeQuery(String myExeQuery) {
				
		try {
			this.myConnection = DriverManager.getConnection(path, user, password);
			Statement myStatement = this.myConnection.createStatement();
			myStatement.executeUpdate(myExeQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
		
	public void disconnect() {
		
		try {
			this.myConnection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
}
