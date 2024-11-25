 

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 * 
 * @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 */

public class CommandWords
{ 
    private static final String[] validCommands = {
        "go", "quit", "help", "pickup", "inspect", "remove",
        "look", "trade","back", "use", "backpack"
    };
    private static final String[] validItemsAndLocations = {
        "supermarket", "pharmacy", "police_station", "residential_building",
        "gas_station", "car_park", "water_bottle", "knife", "first_aid_kit",
        "sandwich","car_battery","gasoline"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    /***
     * check if the second word is a valid word
     */
    public boolean isValidSecondWord(String secondWord)
    {
        for (int i = 0; i < validItemsAndLocations.length; i++) {
            if (validItemsAndLocations[i].equals(secondWord))
                return true;
        }
        // if we get here, the second word is not a valid item or location
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        System.out.println("Valid commands:");
        for (String command : validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();

        System.out.println("Valid items/locations:");
        for (String item : validItemsAndLocations) {
            System.out.print(item + "  ");
        }
        System.out.println();
    }
    


}
