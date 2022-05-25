package com.example;

import java.util.Set;

public class CartSystem extends TheSystem {
    
	CartSystem() { }

    @Override
    public void display() {
        
        System.out.printf("Cart:\n%-20s %-20s %-10s %-10s %-10s\n", "Name", "Description", "Price", "Quantity", "Sub Total");

        Set<String> keys = this.getItemCollection().keySet();
        double preTaxTotal = 0;
        for(String str : keys)
        {
        	Item item = this.getItemCollection().get(str);
        	
        	preTaxTotal += item.getItemPrice() * item.getQuantity();
        	
        	System.out.printf("%-20s %-20s %-10.2f %-10f %-10.0f\n", item.getItemName(), item.getItemDesc(), (float)(double)item.getItemPrice(), (float)(int)item.getQuantity(), (item.getItemPrice() * item.getQuantity()));
        }
        
        System.out.printf("%-20s %-20.2f\n%-20s %-20.2f\n%-20s %-20.2f\n", "Pre-tax Total", preTaxTotal, "Tax", preTaxTotal * 0.05, "Total", preTaxTotal * 1.05);
    }
}