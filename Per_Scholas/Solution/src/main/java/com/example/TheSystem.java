package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public abstract class TheSystem
{

    private HashMap<String, Item> itemCollection;

    TheSystem()
    {
        itemCollection = new HashMap<String, Item>();
        //maybe remove "this"?
        if(this.getClass().getSimpleName().equals("AppSystem"))
        {
        	Path p = Paths.get("resources/sample.txt");
        	List<String> lines = null; try { lines = Files.readAllLines(p); } catch (IOException e) { e.printStackTrace(); }
            
        	String[] data;
        	for(String line : lines)
        	{
        		data = line.split("\\s ");
        		itemCollection.put(data[0], new Item(data[0], data[1], Double.valueOf(data[2]), Integer.parseInt(data[3])));
        	}
        }
    }
    
    public HashMap<String, Item> getItemCollection(){ return itemCollection; }
    public void setItemCollection(HashMap<String, Item> itemCollection) { this.itemCollection = itemCollection; }
    
    public Boolean checkAvailability(Item item)
    {
        if(item.getQuantity() >= item.getAvailableQuantity())
        {
            System.out.println("System is unable to add " + item.getItemName() + " to the card. System only has " + item.getAvailableQuantity() + " " + item.getItemName() + "s.");
            return false;
        }
        return true;
    }
    
    public Boolean add(Item item)
    {
        if(item == null) return false;
        boolean contained = this.itemCollection.containsValue(item);
        if(contained && item.getAvailableQuantity() > 0)
        {
        	item.setQuantity(item.getQuantity() + 1);
            return true;
        } else if(!contained)
        {
	        this.itemCollection.put(item.getItemName(), item);
	        return true;
        }
        return false;
    }

    public Item remove(String itemName)
    {
        if(this.itemCollection.containsKey(itemName)) return this.itemCollection.remove(itemName);
        return null;
    }

    public abstract void display();
}
