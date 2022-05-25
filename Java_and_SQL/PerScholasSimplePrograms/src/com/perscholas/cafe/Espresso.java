package com.perscholas.cafe;

import java.util.Scanner;

public class Espresso extends Product {

	boolean extraShot, macchiato;
	
	Espresso()
	{
		super();
		this.extraShot = false;
		this.macchiato = false;
	}
	
	public Espresso(String name, double price, String description, boolean extraShot, boolean macchiato)
	{
		super(name, price, description);
		this.extraShot = extraShot;
		this.macchiato = macchiato;
	}
	
	
	
	public boolean isExtraShot() {return extraShot;}
	public void setExtraShot(boolean extraShot) {this.extraShot = extraShot;}

	public boolean isMacchiato() {return macchiato;}
	public void setMacchiato(boolean macchiato) {this.macchiato = macchiato;}

	@Override
	public double calculateProductTotal()
	{
		int addOns = (extraShot ? 2 : 0) + (macchiato ? 1 : 0);
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
		System.out.println("Extra shot?");
		this.setExtraShot(s.next().equals("yes") ? true : false);
		System.out.println("Macchiato?");
		this.setMacchiato(s.next().equals("yes") ? true : false);
	}

}
