import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 */

public class Room 
{
    private String description;
    private String name;
    private HashMap<String, Room> exits; 
    private Set<Item> items;
 

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String name) 
    {
        this.description = description;
        this.name = name;
        this.items = new HashSet<>();
    
        exits = new HashMap<>();
    }
   

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
        
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /***
     * return room name as a string
     */
    public String getRoomName(){
        return name;
    }
    /***
     * returns the set of items. for getItemsDescription method
     */
    public Set<Item> getItems(){
        return items;
    }
    /***
     * information for items in rooms
     */
    public String getItemsDescription () {
        if (items.isEmpty()) {
            return "No items in this room";
        }
        StringBuilder itemDescriptions = new StringBuilder("Items in this room: ");
        for (Item item: items) {
            itemDescriptions.append(item).append(" ");
        }
        return itemDescriptions.toString();
    
    
    }
    /***
     *helps to put items into rooms
     */
    public void setItems(Set<Item> items) {
        this.items=items;
    }
}

