package reto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConnectionToDB {
	
	private Connection myConnection;
	
	@SuppressWarnings("finally")
	public ResultSet myQuery(String myQuery) {
		
		ResultSet myResultSet = null;
		
		try {
			//empty password for macOS, root for Windows
			this.myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto_grupo_7", "root", "root");
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
			//empty password for macOS, root for Windows
			this.myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto_grupo_7", "root", "root");
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
