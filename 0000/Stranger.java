 


/**
 * Stranger class for the game
 * 
 * the stranger walks between some rooms.
 * we can also trade items with him.
 * There are multiple trading options which is depended to stranger's location
 * 
 * @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 */

public class Stranger
{
    private Room strangerRoom; // gas_station or residential_building
    private Room gas_station;
    private Room residential_building;
    private Game game;
    private Inventory inventory;
    
    /**
     * Constructor for objects of class Stranger
     */
    public Stranger(Game game, Room gas_station, Room residential_building)
    {   
        this.gas_station = gas_station;
        this.residential_building = residential_building;
        this.strangerRoom = gas_station;
        this.game = game;
    }
    
    /**
     * A method to make stranger walk.
     * When we walk through rooms 3 times, the stranger walks to another room.
     */
    public void strangerMovement()
    {
        if (game !=null) {
            //every time we move, icrement increases by one. If it is divisible by 3, make the stranger move
            int playerMovementCount = game.getPlayerMovementCount();
        
            if (playerMovementCount % 3 == 0) {
                move();
            }
        
        }
    }
    /***
     * helper method for strangerMovement. I clarified which rooms
     * the stranger should go
     */
    public void move() {
        //telling stranger where to go
        if (strangerRoom == gas_station) {
            strangerRoom = residential_building;
        } else {
            strangerRoom=gas_station;
        }
    }
    
    /***
     * a method to trade items with stranger
     * if we give him knife and medkit in gas station, he gives us gasoline
     * if we give him sandwich and water bottle in residential building,
     * he gives us car engine
     * 
     * We need car engine and gasoline to finish the game
     */
    public void tradeItems(Inventory inventory){
        if (strangerRoom.equals(gas_station)) {
            //call methods if you did the requirements. If not, tell what is missing
            if(inventory.hasItem("knife") && inventory.hasItem("medkit")){
                
                
                
                inventory.removeItem("knife");
                inventory.removeItem("medkit");
                inventory.pickupItem("gasoline");
                System.out.println("Trade completed");
                System.out.println("Now you have gasoline");
                inventory.getTotalWeight();

                
            } else{
                System.out.println("You don't have knife and medkit");
            }
        } else if (strangerRoom.equals(residential_building)) {
            if(inventory.hasItem("sandwich") && inventory.hasItem("water_bottle")){

                
                inventory.removeItem("sandwich");
                inventory.removeItem("water_bottle");
                inventory.pickupItem("car_engine");
                System.out.println("Trade completed");
                System.out.println("Now you have car_engine");
                inventory.getTotalWeight();

                
            } else{
                System.out.println("You don't have sandwich and water_bottle");
            }
        }else {
            System.out.println("You are not in either gas station or residential building");
        }
            
    }

    /***
     * helper method to get which room the stranger is
     * as a Room
     */
    public Room getStrangerRoom(){    
        return strangerRoom;
    }
    
    /***
     * helper method to check if the stranger is in gas station or not
     */
    public  boolean isStrangerInGasStation(){
        if (getStrangerRoom().equals(gas_station)) {
            return true;
        } else {
            return false;
        }
    }
    
    /***
     * helper method to check if the stranger is in residential building or not
     */
    public boolean isStrangerInResidentialBuilding() {
        if(getStrangerRoom().equals(residential_building)) {
            return true;
        } else {
            return false;
        }
    }  
}   

