import java.util.Scanner;

/**
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
* @author  Michael Kölling, David J. Barnes, and Efe Katircioglu
 * @version 2024.25.11
 * 

 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input
    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     * 
     * added the third word, only being called when needed
     */
    public Command getCommand() 
    {
        String inputLine;   
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");     

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();     
                if (tokenizer.hasNext()) {
                    word3 = tokenizer.next();
                }
            }
        }

        if(commands.isCommand(word1)) {
            if ("trade".equals(word1) && "stranger".equals(word2)) {
                return new Command(word1,word2,word3);
            }
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
