package reto;

import java.sql.*;

public class ConnectionToDB {
	
	public ResultSet myQuery(String myQuery) {
		
		ResultSet myResultSet = null;
		
		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto_grupo_7", "root", "root");
			Statement myStatement = myConnection.createStatement();
			myResultSet = myStatement.executeQuery(myQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			return myResultSet;
		}
	}
		
	public void disconnect() {
		
	}

}
