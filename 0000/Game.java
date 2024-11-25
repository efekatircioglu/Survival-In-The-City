 

import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.ArrayList;
/**
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 *  It also calles or creates most of the action methods
 *  alongside with some helper methods.
 *  
 * @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 */


public class Game 
{
    private Parser parser;
    public Room currentRoom;
    private Command command;
    private Item item;
    private Inventory inventory;
    private Room tent, supermarket, pharmacy, police_station, gas_station, residential_building, car_park, shelter, metro;
    private ArrayList<Room> roomHistory;
    private Stranger stranger;
    private int playerMovementCount = 0;

    /**
     * Create the game and initialise its internal map. Also create some useful
     * information for other classes and methods.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomHistory = new ArrayList<>();
        this.inventory = new Inventory(this);
        this.stranger = new Stranger(this, gas_station, residential_building);
    
    }

    /**
     * Create all the rooms and link their exits together. also create
     * items and put items in rooms.
     */
    private void createRooms()
    {

        
        // create the rooms
        tent = new Room("in the tent", "tent");
        supermarket = new Room("in the supermarket","supermarket");
        pharmacy = new Room("in the pharmacy","pharmacy");
        police_station = new Room("in the police station","police_station");
        gas_station = new Room("in the gas station","gas_station");
        residential_building = new Room("in the residential building","residential_building");
        car_park = new Room("in the car park", "car_park");
        shelter = new Room("in the shelter", "shelter");
        metro = new Room("in the metro", "metro");

        
        // initialise room exits
        tent.setExit("south", gas_station);
        tent.setExit("east", supermarket);
        supermarket.setExit("west", tent);
        supermarket.setExit("south", pharmacy);
        supermarket.setExit("east", metro);
        pharmacy.setExit("north", supermarket);
        pharmacy.setExit("south", residential_building);
        pharmacy.setExit("east", police_station);
        police_station.setExit("west", pharmacy);
        police_station.setExit("south",car_park);
        police_station.setExit("north",metro);
        gas_station.setExit("north", tent);
        gas_station.setExit("east", residential_building);
        residential_building.setExit("north", pharmacy);
        residential_building.setExit("west", gas_station);
        residential_building.setExit("east", car_park);
        car_park.setExit("west",residential_building);
        car_park.setExit("north",police_station);

        currentRoom = tent;  // start game outside
        
        //create items
        Item sandwich = new Item("sandwich");
        Item water_bottle = new Item("water_bottle");
        Item medkit = new Item("medkit");
        Item knife = new Item("knife");
        Item gasoline = new Item("gasoline");
        Item car_engine = new Item("car_engine");
        Item bag = new Item("bag");
        
        //add items to rooms
        addItemToRoom(supermarket, sandwich);
        addItemToRoom(supermarket, water_bottle);
        addItemToRoom(pharmacy, medkit);
        addItemToRoom(police_station, knife);
        addItemToRoom(police_station, bag);

        // Displaying room descriptions and items in the rooms
        displayRoomInfo(supermarket);
        displayRoomInfo(tent);
        displayRoomInfo(pharmacy);
        displayRoomInfo(police_station);
        displayRoomInfo(gas_station);
        displayRoomInfo(residential_building);        
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();       
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }        
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to my game 'Survival In the City '");
        System.out.println("You are in tent, you have to go to the Shelter");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        //when the first word is equal the command, call them
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
            playerMovementCount++;
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("pickup")) {
            String itemName = command.getSecondWord();
            if (Item.itemMap.containsKey( itemName)) {
                inventory.pickupItem( itemName);
            } else {
                System.out.println("pickup what?");
            }   
        } else if (commandWord.equals("inspect")) {
            String itemName = command.getSecondWord();
            if (Item.itemMap.containsKey(itemName)){
                inventory.inspectItem(itemName);
            } else {
                System.out.println("Inspect what?");
            }   
        } else if (commandWord.equals("remove")) {
            String itemName = command.getSecondWord();
            if (Item.itemMap.containsKey(itemName)) {
                inventory.removeItem(itemName);
            } else {
                System.out.println("Remove what?");
            } 

        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("trade")) {
            //if you enter 'trade stranger items' call the trade method. If not, don't call it
            if(command.getSecondWord().equals("stranger") && command.hasThirdWord() && command.getThirdWord().equals("items")) {
                stranger.tradeItems(inventory);
            } else {
                System.out.println("trade what, with who?");
            }
            
        } else if (commandWord.equals("back")) {
            back();
        } else if (commandWord.equals("use")) {
            useItemsForEscape(inventory); 
        } else if (commandWord.equals("backpack")) {
            inventory.displayInventory();
        }
        return wantToQuit;
    }    

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are at"+ currentRoom);
        System.out.println();
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * if next room is metro, go to a random room.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
        stranger.strangerMovement();
        // in this method the first word is always 'go' and second word is a direction.
        //If the next room is metro, transport it to somewhere in the game
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.getRoomName().equals("metro")) { 
            Room[] destinations = {tent, supermarket, pharmacy, gas_station, police_station,residential_building};
            Random random = new Random();
            Room randomRoom = destinations[random.nextInt(destinations.length)];
            System.out.println("You are in the metro...");
            System.out.println("Transportation completed. " + randomRoom.getLongDescription() + ".");
            currentRoom = randomRoom;
        }
        else {
            if (!currentRoom.getRoomName().equals("metro")){
                roomHistory.add(currentRoom);
            }
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());  
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /***
     * a method which helps the player to see which room they are in,
     * to see what items are there
     * and to see if stranger is here or not
     */
    public void look() {
        System.out.println("You are in " + currentRoom.getRoomName());
        Set<Item> itemsInRoom = currentRoom.getItems();
        //if there is not items, don't print anything. If there's items print them. also see stranger is here or not
        if (itemsInRoom.isEmpty()) {
            System.out.println("There are no items in this room");
        } else {
            System.out.println("There are these items in this room: " );
            for (Item item : itemsInRoom) {
                System.out.println("-" + item.getItemName());
            }
        }
        
        if((stranger.isStrangerInGasStation() == true) && (currentRoom.equals(gas_station))) {
           System.out.println("Stranger is here in the gas station."); 
        }else if (stranger.isStrangerInResidentialBuilding() && currentRoom.equals(residential_building)) {
            System.out.println("Stranger is here in the residential building.");
        } else {
            System.out.println("Stranger is not here.");
        } 
           
    }
     /***
      * return currentRoom which is a Room object.
      * valuable information, and it's being called often by other methods
      */ 
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /***
      * the method that makes the game end.
      * you need to be in the car park
      * and you need car engine and gasoline
      * when you all have that, use the car to
      * go to shelter(where the game ends)
      */
    public void useItemsForEscape(Inventory inventory) {
        //if you meet all the conditions, the method is called and the game will end.
        if (currentRoom==car_park) {
            boolean hasCarEngine = false;
            boolean hasGasoline = false;
            for(Item item : inventory.getItems()) {
                if (item.getItemName().equals("car_engine")) {
                    hasCarEngine = true;
                } else if (item.getItemName().equals("gasoline")) {
                    hasGasoline = true;
                }
            }
            if (hasCarEngine && hasGasoline) {
                System.out.println("You have the car engine and gasoline");
                System.out.println("You have reached the shelter");
                System.out.println("The game ends now. You won");
                currentRoom = shelter;
                System.out.println(currentRoom.getLongDescription());
                System.exit(0);   
            } else {
                System.out.println("You need both car engine and gasoline to escape");
            } 
        } else {
            System.out.println("You need to be at the car park to escape");
        }
    }
    
    /***
     * go back to the last room
     */
    public void back() {
        //I created roomHistory to track back method. If your room is not empty, call the last element and make it current room
        if (roomHistory.isEmpty()) {
            System.out.println("You can't go back");
            return;
        } 
        Room previousRoom = roomHistory.remove(roomHistory.size()-1);
        currentRoom = previousRoom;
        System.out.println("You are back " + currentRoom.getShortDescription());
    }
    /***
     * a method to put items in rooms
     */
    private static void addItemToRoom(Room room, Item item) {
        Set<Item> currentItems = room.getItems();
        currentItems.add(item);
        room.setItems(currentItems);
    }
    /***
     * a method to print information for the room's items
     */
    private static void displayRoomInfo(Room room) {
       System.out.println(room.getItemsDescription());
    }
    /***
     * returns the track of player's movement
     * this track is being used to move stranger
     */
    public int getPlayerMovementCount() {
        return playerMovementCount;
    }
    

}
      


