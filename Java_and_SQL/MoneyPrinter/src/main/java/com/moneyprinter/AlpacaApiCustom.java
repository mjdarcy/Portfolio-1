package com.moneyprinter;

import java.util.HashMap;
import java.util.LinkedList;

import com.moneyprinter.UserData.Stock;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.orders.enums.OrderSide;
import net.jacobpeterson.alpaca.model.properties.DataAPIType;
import net.jacobpeterson.alpaca.model.properties.EndpointAPIType;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;

public class AlpacaApiCustom extends AlpacaAPI {

	LinkedList<Stock> stocks;
	boolean error = false;
	
	AlpacaApiCustom(String key, String secret)
	{
        super(key, secret, EndpointAPIType.LIVE, DataAPIType.SIP);
        //The below SHOULD truly stop the program when exited.
    	Runtime.getRuntime().addShutdownHook(new Thread() { public void run() {
			getOkHttpClient().dispatcher().executorService().shutdown();
			getOkHttpClient().connectionPool().evictAll();}});
	}
	
	double getEquity() throws NumberFormatException, AlpacaClientException { return Double.valueOf(account().get().getEquity()); };

	public HashMap<String, Integer> stockDiffs(LinkedList<Stock> stocks, int maxEquity) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for(Stock stock : stocks)
		{
			double stockPortValue = 0;
			try { stockPortValue = Double.valueOf(positions().getBySymbol(stock.symbol).getMarketValue()); } catch (Exception e) { e.printStackTrace(); }
			int target = (int) (maxEquity * stock.fraction);
			int difference = target - (int) stockPortValue;
			if(difference > 0) result.put(stock.symbol, difference);
		}
		return result;
	}

	public void buy(String symbol, Integer amount) {
		System.out.println(symbol);
		try { orders().requestNotionalMarketOrder(symbol, (double) amount, OrderSide.BUY); } catch (AlpacaClientException e) { e.printStackTrace(); }
	}
	
	boolean isOpen() { try { return clock().get().getIsOpen(); } catch (AlpacaClientException e) { e.printStackTrace();} return false; }
}
