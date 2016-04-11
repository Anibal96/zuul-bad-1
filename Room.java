import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    public HashMap<String, Room> salidas;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room neighbor) 
    {
        salidas.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * devuelve el objeto de la clase Room asociado a esa salida
     */
    public Room getExit(String salida)
    {
        Room nextExit = null;
        if(salida.equals("north")) {
            nextExit = salidas.get("north");
        }
        if(salida.equals("east")) {
            nextExit = salidas.get("east");
        }
        if(salida.equals("south")) {
            nextExit = salidas.get("south");
        }
        if(salida.equals("west")) {
            nextExit = salidas.get("west");
        }    
        if(salida.equals("southEast")) {
            nextExit = salidas.get("southEast");
        }
        if(salida.equals("northEast")) {
            nextExit = salidas.get("northEast");
        }
        return nextExit;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String exits = null;
        if(salidas.containsKey("north")) {
            exits = "north ";
        }
        if(salidas.containsKey("east")) {
            if(exits == null){
                exits = "east ";
            }
            else{
                exits = exits + "east ";
            }
        }
        if(salidas.containsKey("south")) {
            if(exits == null){
                exits = "south ";
            }
            else{
                exits = exits + "south ";
            }
        }
        if(salidas.containsKey("west")) {
            if(exits == null){
                exits = "west ";
            }
            else{
                exits = exits + "west ";
            }
        }
        if(salidas.containsKey("southEast")) {
            if(exits == null){
                exits = "southEast ";
            }
            else{
                exits = exits + "southEast ";
            }
        }
        if(salidas.containsKey("northEast")) {
            if(exits == null){
                exits = "northEast ";
            }
            else{
                exits = exits + "northEast ";
            }
        }
        return exits;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + "\n" + "Exits: " + getExitString();
    }
}
