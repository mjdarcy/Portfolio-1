package com.example;

import java.util.Set;

public class AppSystem extends TheSystem
{
    
    AppSystem() { }

    @Override
    public void display() {
        System.out.printf("AppSystem Inventory:\n%-20s %-20s %-10s %-10s", "Name", "Description", "Price", "Available Quantity\n");
        
        Set<String> keys = this.getItemCollection().keySet();
        for(String str : keys)
        {
        	Item item = this.getItemCollection().get(str);
        	System.out.printf("%-20s %-20s %-10.2f %-10.0f\n", item.getItemName(), item.getItemDesc(), (float)((double) item.getItemPrice()), (float)((int)item.getAvailableQuantity()));
        }
    }

    @Override
    public Boolean add(Item item) {
        if(item == null) return false;
        String name = item.getItemName();
        if(this.getItemCollection().containsKey(name))
        {
        	System.out.println(name + " is already in the App System");
        	return false;
        }
        this.getItemCollection().put(name, item);
        return true;
    }

    public Item reduceAvailableQuantity(String itemName) {
    	if(this.getItemCollection().containsKey(itemName))
    	{
    		Item foundItem = this.getItemCollection().get(itemName);
    		foundItem.setAvailableQuantity(foundItem.getAvailableQuantity() - 1);
    		if(foundItem.getAvailableQuantity() < 1) this.getItemCollection().remove(itemName);
    		return foundItem;
    	}
    	return null;
    }
}
