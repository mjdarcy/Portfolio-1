package com.perscholas.cafe;

import java.util.Scanner;

public class Coffee extends Product {
	
	boolean sugar, milk;
	
	Coffee()
	{
		super();
		this.sugar = false;
		this.milk = false;
	}
	
	public Coffee(String name, double price, String description, boolean sugar, boolean milk)
	{
		super(name, price, description);
		this.sugar = sugar;
		this.milk = milk;
	}



	@Override
	public double calculateProductTotal() { return this.getPrice() * this.getQuantity(); }

	public boolean isSugar() { return sugar; }
	public void setSugar(boolean sugar) { this.sugar = sugar; }

	public boolean isMilk() { return milk; }
	public void setMilk(boolean milk) { this.milk = milk; }

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOptions(Scanner s) {
		System.out.println("Sugar?");
		this.setSugar(s.next().equals("yes") ? true : false);
		System.out.println("Milk?");
		this.setMilk(s.next().equals("yes") ? true : false);
	}
	
	

}
