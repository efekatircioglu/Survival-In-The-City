 

import java.util.HashMap;
import java.util.Map;

/***
 * Item class for my game, it creates items, creates their weight
 * assigns items and their weights.
 * 
 * There are also some helper methods for other methods to work as intended.
 * 
 * 
* @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 */

public class Item
{
    private String itemName;
    private int itemWeight;
    
    public static final Map<String, Integer> itemMap = new HashMap<>();
    
    static {
        //items and their weights. I use map to make sure that weights and items are connected.
        itemMap.put("knife",3);
        itemMap.put("water_bottle",4);
        itemMap.put("sandwich",3);
        itemMap.put("medkit",5);
        itemMap.put("car_engine",1);
        itemMap.put("gasoline",1);
        itemMap.put("bag",0);
        
    }

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemName)
    {
        this.itemName=itemName;
        this.itemWeight=itemMap.getOrDefault(itemName,0);
    }
    /***
     * returns item name as a string
     */
    public String getItemName(){
        return itemName;
    }
    /***
     * returns weight of item as int
     */
    public int getItemWeight(){
        return itemWeight;
    }
    /**
     * removes an item from the item map
     */
    public  void removeItemFromMap(String itemName) {
        if (itemMap.containsKey(itemName)) {
            itemMap.remove(itemName);
            System.out.println(itemName + " removed from item map.");
        } else {
            System.out.println(itemName + " not found in item map.");
        }
    }

}



