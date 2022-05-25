package com.perscholas.cafe;

import java.util.Scanner;

public class Cappuccino extends Product {

	boolean peppermint, whippedCream;
	
	Cappuccino()
	{
		super();
		this.peppermint = false;
		this.whippedCream = false;
	}
	
	public Cappuccino(String name, double price, String description, boolean peppermint, boolean whippedCream)
	{
		super(name, price, description);
		this.peppermint = peppermint;
		this.whippedCream = whippedCream;
	}
	
	public boolean isPeppermint() {return peppermint;}
	public void setPeppermint(boolean peppermint) {this.peppermint = peppermint;}

	public boolean isWhippedCream() {return whippedCream;}
	public void setWhippedCream(boolean whippedCream) {this.whippedCream = whippedCream;}

	@Override
	public double calculateProductTotal()
	{
		int addOns = (peppermint ? 2 : 0) + (whippedCream ? 1 : 0);
		return (this.getPrice() + addOns) * this.getQuantity();
	}

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
		System.out.println("Peppermint?");
		this.setPeppermint(s.next().equals("yes") ? true : false);
		System.out.println("Whipped cream?");
		this.setWhippedCream(s.next().equals("yes") ? true : false);
	}
}
