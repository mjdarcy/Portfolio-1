package com.perscholas.cafe;

import java.util.Scanner;

public class CafeApp {

	public static void main(String[] args) 
	{
		Product coffee = new Coffee("Coffee", 2.0, "It's coffee.", false, false);
		Product espresso = new Espresso("Espresso", 3.0, "It's an espresso.", false, false);
		Product cappuccino = new Cappuccino("Cappuccino", 4.0, "It's a cappuccino.", false, false);
		Product[] products = {coffee, espresso, cappuccino};
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter quantities and options for: ");
		int sales = 0;
		double salesSubTotal = 0;
		for(Product p : products) 
		{
			System.out.println(p.getName() + " quantity: ");
			p.setQuantity(s.nextInt());
			p.setOptions(s);
			System.out.println("Name: " + p.getName() + ", Description: " + p.getDescription() + ", Product subtotal: $" + p.calculateProductTotal());
			sales += p.getQuantity();
			salesSubTotal += p.calculateProductTotal();
		}
		
		double formatedTotal = Math.round(salesSubTotal * 100.0) / 100.0;
		double formatedTax = Math.round(salesSubTotal * 100.0 * 0.06) / 100.0;
		
		System.out.println("Sales: " + sales + ", Sales subtotal: $" + formatedTotal + ", Sales tax: $" + formatedTax);
		s.close();
	}
}