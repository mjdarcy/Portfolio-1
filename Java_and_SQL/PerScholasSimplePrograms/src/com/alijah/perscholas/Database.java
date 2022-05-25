package com.alijah.perscholas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database 
{
	Database()
	{
		new PreparedStatementParameterExample();
	}
	
	class PreparedStatementParameterExample {
		
		String driverName = "com.mariadb.jdbc.Driver";
		Connection con = null;
		String url = "jdbc:mariadb://localhost:3306/test";
		String username = "root";
		String password = "LyokoFroYo0!";

		public PreparedStatementParameterExample() {
			try { Class.forName(driverName); } catch (ClassNotFoundException e) { System.out.println(e.toString()); }
			try { run(); } catch (SQLException e) { e.printStackTrace(); }
		}

		public void run() throws SQLException {
			
			try { con = DriverManager.getConnection(url, username, password); } catch (SQLException e) { e.printStackTrace(); }
			ResultSet resultSet = null;
			String insertQuery = "INSERT INTO student(RollNo,Name,Course,Address) VALUES(?,?,?,?)";
			
			try {
				PreparedStatement s = con.prepareStatement(insertQuery);
				s.setInt(1, 2);
				s.setString(2, "Dinesh");
				s.setString(3, "MCA");
				s.setString(4, "Patna");
				s.executeUpdate();
				s.close();
				
				String updateQuery = "UPDATE student SET Name=?";
				update(con.prepareStatement(updateQuery), "Computer Science");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				con.close();
			}
		}
		
		void update(PreparedStatement s, String str)
		{
			try {
				s.setString(1, str);
				s.executeUpdate();
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
