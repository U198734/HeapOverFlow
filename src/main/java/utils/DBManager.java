package utils;

import java.sql.*;

public class DBManager {
	
	private Connection connection = null;
	
	public DBManager() {
		
		// WITH MYSQL
		String user = "mysql";
		String password= "prac";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/epaw?serverTimezone=UTC&user="+user+"&password="+password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//execute queries
	public PreparedStatement prepareStatement(String query) {
		
		PreparedStatement statement = null;
		try {
			 statement = connection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statement;
	}
	
	public void finalize() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}