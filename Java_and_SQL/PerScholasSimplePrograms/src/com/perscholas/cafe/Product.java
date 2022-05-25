package com.perscholas.cafe;

import java.util.Scanner;

public abstract class Product
{
	
	private String name;
	private double price;
	private String description;
	private int quantity;
	
	public Product() { }
	
	public Product(String name, double price, String description)
	{
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
	public abstract double calculateProductTotal();

	public abstract void addOptions();
	public abstract void printOptions();
	public abstract void setOptions(Scanner s);
}
