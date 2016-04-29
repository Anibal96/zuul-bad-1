import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private HashMap<String,Option>comandos;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        comandos = new HashMap<String,Option>();
        comandos.put("andare",Option.GO);
        comandos.put("smettere",Option.QUIT);
        comandos.put("aiuto",Option.HELP);
        comandos.put("guarda",Option.LOOK);
        comandos.put("mangiare",Option.EAT);
        comandos.put("ispezionare",Option.INSPECT);
        comandos.put("indietro",Option.BACK);
        comandos.put("prendere",Option.TAKE);
        comandos.put("far-cadere",Option.DROP);
        comandos.put("articolo",Option.ITEMS);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return comandos.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        System.out.println(comandos.keySet());
    }
    
    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord)
    {
        Option option = Option.UNKNOWN;
        if(comandos.containsKey(commandWord)){
            option = comandos.get(commandWord);
        }
        return option;
    }
    
}
