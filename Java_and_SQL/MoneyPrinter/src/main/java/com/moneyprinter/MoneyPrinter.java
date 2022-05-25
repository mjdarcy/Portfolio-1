package com.moneyprinter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.moneyprinter.UserData.Stock;

public class MoneyPrinter
{
	
	final int MILI_IN_MIN = 60000;
	UserData data;
	PlaidApiCustom plaidAPI;
	AlpacaApiCustom alpacaAPI;
	SeleniumApiCustom seleniumAPI;
	boolean running;
	UI ui;
	
	MoneyPrinter()
	{
		data = new UserData("alijahjchandler@gmail.com");
		//I want the below to act as static classes, but I don't want to pass in the API each time. They're essentially helpers.
		//Maybe I could use inheritence here.
		alpacaAPI = new AlpacaApiCustom(data.alpaca_public_key, data.alpaca_secret_key);
		seleniumAPI = new SeleniumApiCustom(data.username, data.password);
		try { plaidAPI = new PlaidApiCustom(data.gmail_json); } catch (Exception e) { running = false; e.printStackTrace(); }
		//ui = new UI();
		//running set to false if the error boolean of any of the APIs are true;
		running = true;
		while(running)
		{
			int excessCash = 0;
			try { excessCash = plaidAPI.excessCash(data.bank_account_max); } catch(Exception e) { e.printStackTrace(); }
			seleniumAPI.transfer(excessCash);
			if(alpacaAPI.isOpen())
			{
				try { data.setAlpacaMaxEquity((int) alpacaAPI.getEquity());} catch(Exception e) { e.printStackTrace(); }
				HashMap<String, Integer> stocksToBuy = alpacaAPI.stockDiffs(data.stocks, data.alpaca_max_equity);
				for(String key : stocksToBuy.keySet())
				{
					int value = stocksToBuy.get(key);
					alpacaAPI.buy(key, value);
					System.out.println("Bought $" + value + " of " + key);
				}
			}
			//I could have the check within the APIs but then I'd need to put it in each method.
			//I could pass over the API throwing an error instead of ending the program.
			//The UI could have a red/yellow/green light for each API, letting the user know which functions of the program are being skipped.
			if(plaidAPI.error || alpacaAPI.error || seleniumAPI.error) running = false;
			try { Thread.sleep(MILI_IN_MIN); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	class UI extends JFrame
	{
		
		JPanel currentPanel;
		
		UI()
		{
			JPanel homeScreen = homeScreen();
			
			this.add(homeScreen);
			this.setSize(600, 500);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
		}
		
		JPanel navigation()
		{
			return null;
		}
		
		JPanel homeScreen()
		{
			//JButton home = new JButton("Home");
			BarGraph bar = new BarGraph();
			
			JPanel home = new JPanel();
			
			home.add(bar);
			
			return home;
			
		}
		
		JPanel options()
		{
			return null;
		}
		
		void update() 
		{

		}
		
		class BarGraph extends JComponent
		{
			public void paintComponent(Graphics g)
			{
				//x and y axis
				int xMargin = 50;
				int yMargin = 50;
				int xLength = this.getWidth() - xMargin;
				int yLength = this.getHeight() - yMargin;
				
				g.drawLine(xMargin, yLength, xLength, yLength);
				g.drawLine(xMargin, yLength, xMargin, yMargin);
				
				//y interval lines
				int numberOfLines = 5 * (yLength / 200);
				
				for(int i = 0; i < numberOfLines; i++)
				{					
					int lineY = yMargin + (i * ((yLength-yMargin)/numberOfLines));
					g.drawLine(xMargin-2, lineY, xMargin+2, lineY);
					int lineInterval = 10000 * (numberOfLines-i)/numberOfLines;
					String lineAbrev = String.valueOf( (double) lineInterval / (1000 * Math.round(Math.log10(lineInterval) / 3))) + "k";
					//place values:
					g.drawString(lineAbrev, xMargin - (lineAbrev.length() * 8), lineY + 4);
				}
				
				//draw bars
				/*
				for(int i = 0; i < data.stocks.size(); i++)
				{
					String name = tickers.get(i).getSymbol();
					String value = tickers.get(i).getMarketValue();
					double intValue = Double.parseDouble(value) / 10000 * yLength;
					g.setColor(Color.red);
					g.fillRect(xMargin - 20 + 40 * (i+1), yLength, 10, (int) -intValue + yMargin/2);
					g.setColor(Color.black);
					g.drawString(name, xMargin - 30 + 40 * (i+1), yLength + 15);
				}*/
			}
		}
	}
	
    public static void main(String... args) { new MoneyPrinter(); }
}