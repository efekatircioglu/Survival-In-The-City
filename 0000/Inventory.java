import java.util.HashSet;
import java.util.Set;
/**
 * Inventory class, where all your items rest.
 * There are important methods like
 * pickup item, remove item, inspect item methods.
 * And also there are some helper methods to execute as intended.
 * 
 * @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 */
public class Inventory
{
    private final Set<Item>items;
    private int max_weight = 10;
    private Game game;


    /**
     * Constructor for objects of class Inventory
     */
    public Inventory(Game game)
    {
        this.items = new HashSet<>();
        this.game = game;
    }

    /**
     * pickup an item to your inventory
     * if you pickup a bag, it increases your max weight capacity
     */
    public void pickupItem(String itemName)
    
    {   
        // if the item exist with out current room, pickup the item. 
        if (!Item.itemMap.containsKey(itemName)) {
            System.out.println(itemName + " is not a valid item.");
            return; 
        }
        
        if (itemName.equals("bag")) {
        max_weight += 10; 
        System.out.println("You picked up a bag. Now you can carry " + max_weight + " kg.");
        
        }
        
        Room currentRoom = game.getCurrentRoom();
        String currentRoomName = currentRoom.getRoomName();
        
        //if we randomly call an item not in the room that the item exist, tell the player from where we should get it
        if ((itemName.equals("sandwich") || itemName.equals("water_bottle")) && !currentRoomName.equals("supermarket")) {
            System.out.println("You can only pick up " + itemName + " in the supermarket.");
            return;
        } else if (itemName.equals("medkit") && !currentRoomName.equals("pharmacy")) {
            System.out.println("You can only pick up " + itemName + " in the pharmacy.");
            return;
        } else if (itemName.equals("knife") && !currentRoomName.equals("police_station")) {
            System.out.println("You can only pick up " + itemName + " in the police station.");
            return;
        }
        
        
        
        
        Item item = new Item(itemName);
        int potentialTotalWeight = getTotalWeight() + item.getItemWeight();
        
        //if item is already in our inventory, say it. If we exceed the max kilos that we have, clarify it
        if (items.contains(item)) {
            System.out.println(itemName + " is already in your inventory");
        } else if (potentialTotalWeight > max_weight) {
            System.out.println("Cannot add " + itemName + " because you can carry 10 kilos at the same time.");
        } else{
            items.add(item);
            System.out.println(itemName + " added to inventory.");
            System.out.println("Total weight: " + getTotalWeight() + " kg.");
        }

    }
    /***
     * remove an item from your inventory
     */
    public void removeItem(String itemName) {
        //remove the item from our inventory if we have that item
        if (items.contains(itemName)) {
            int total_weight = getTotalWeight();
            for(Item item:items) {
                total_weight -= item.getItemWeight();
            }
            items.remove(itemName);
            Item.itemMap.remove(itemName);
            
        
            System.out.println(itemName + " removed from inventory");
            System.out.println("You are now carrying " + getTotalWeight());
        } else {
            System.out.println(itemName + " is not in your inventory");
        }
    }


    /***
     * get the weight of an item, from your inventory
     */
    public void inspectItem(String itemName) {
        //get item information
        Item item = new Item(itemName);
        if (items.contains(item)) {
            System.out.println(itemName + " weights " + item.getItemWeight() + " kg.");
        } else {
            System.out.println(itemName + " is not found in your inventory.");
        }
        
    }
    /***
     * get items from your inventory and their weights 
     */
    public void displayInventory() {
        //print info about our inventory
        if (items.isEmpty()) {
        System.out.println("Your inventory is empty. ");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("Total weight: " + getTotalWeight() + " kg.");
        }
    }
    
    /***
     * return the total weight that you carry.
     */
    public int getTotalWeight() {
        int totalWeight=0;
        for (Item item: items) {
            
            totalWeight += item.getItemWeight();
        }
        
        
        return totalWeight;
    }
    /***
     * get an item's weight, it's an helper method for other methods
     */
    public int getItemWeight(String itemName) {
        for (Item item: items) {
            if (item.getItemName().equals(itemName)) {
                return item.getItemWeight();
            }
        }
        System.out.println(itemName + " not found in the inventory.");
        return 0;
    }
    /***
     * check for player if he has the specific item or not
     */
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
            
        }
        return false;
    }
    /***
     * helper method, return all items from your inventory
     */
    public Set<Item> getItems() {
        return items;
    }
    
    
}
