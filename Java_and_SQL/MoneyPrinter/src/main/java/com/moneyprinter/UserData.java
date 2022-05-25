package com.moneyprinter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UserData
{
	Connection con;
	Statement stm;
	//User info - alpaca
	String username;
	String password;
	String alpaca_public_key;
	String alpaca_secret_key;
	int alpaca_max_equity;
	LinkedList<Stock> stocks;
	//User info - gmail
	int bank_account_max;
	
	//Server info. Server/UI responsibilities a bit jumbled since this is a personal app.
	String gmail_json;
	
	UserData(String username, String password, String alpaca_public_key, String alpaca_secret_key, int bank_account_max, int alpaca_max_equity, String gmail_json)
	{	
		//Could use this constructor to add a new user to the database. If no user found, create a form for the user.
		this.username = username;
		this.password = password;
		this.alpaca_public_key = alpaca_public_key;
		this.alpaca_secret_key = alpaca_secret_key;
		this.bank_account_max = bank_account_max;
		this.alpaca_max_equity = alpaca_max_equity;
		this.stocks = new LinkedList<Stock>();
		this.gmail_json = gmail_json;
	}
	
	UserData(String username)
	{
		//MAKE SURE TO SEND CUSTOM USERNAME SELECT REQUEST PROPERLY. And to fix other custom queries.
		try
		{
			con = DriverManager.getConnection("jdbc:sqlite:../../../data.db", "", "");
			stm = con.createStatement();
			
			String getUserData = "SELECT * FROM User WHERE username = 'alijahjchandler@gmail.com'";
			String getUserStocks = "SELECT * FROM Stock WHERE username = 'alijahjchandler@gmail.com'";
			
			ResultSet rs = stm.executeQuery(getUserData);
			if(rs != null)
			{
				this.username = rs.getString("username");
				this.password = rs.getString("password");
				this.alpaca_public_key = rs.getString("alpaca_public_key");
				this.alpaca_secret_key = rs.getString("alpaca_secret_key");
				this.bank_account_max = rs.getInt("bank_account_max");
				this.alpaca_max_equity = rs.getInt("alpaca_max_equity");
				this.gmail_json = rs.getString("gmail_json");
			} //else create new User
			rs = stm.executeQuery(getUserStocks);
			this.stocks = new LinkedList<Stock>();
			while(rs.next()) stocks.add(new Stock(rs.getString("symbol"), rs.getDouble("fraction")));
		} catch (SQLException e) { e.printStackTrace(); }
	}

	public static class Stock
	{
		String symbol;
		double fraction;
		
		Stock(String symbol, double fraction)
		{
			this.symbol = symbol;
			this.fraction = fraction;
		}
	}
	
	boolean setAlpacaMaxEquity(int alpacaEquity)
	{
		System.out.println("Current max equity is " + this.alpaca_max_equity + ". ");
		if(alpacaEquity > this.alpaca_max_equity)
		{
			this.alpaca_max_equity = alpacaEquity;
			System.out.println(", set it to " + this.alpaca_max_equity + ".");
			try { stm.executeUpdate("UPDATE User SET alpaca_max_equity = " + this.alpaca_max_equity + " WHERE username = 'alijahjchandler@gmail.com'"); } catch (SQLException e) { e.printStackTrace(); }
			return true;
		}
		return false;
	}
	
	
}