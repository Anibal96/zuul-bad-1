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
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room southEastExit;
    public Room northEastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room noroEste) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null)
            southEastExit = southEast;
        if(noroEste != null)
            northEastExit = noroEste;
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
            nextExit = northExit;
        }
        if(salida.equals("east")) {
            nextExit = eastExit;
        }
        if(salida.equals("south")) {
            nextExit = southExit;
        }
        if(salida.equals("west")) {
            nextExit = westExit;
        }    
        if(salida.equals("southEast")) {
            nextExit = southEastExit;
        }
        if(salida.equals("northEast")) {
            nextExit = northEastExit;
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
        if(northExit != null) {
            exits = "north ";
        }
        if(eastExit != null) {
            if(exits == null){
                exits = "east ";
            }
            else{
                exits = exits + "east ";
            }
        }
        if(southExit != null) {
            if(exits == null){
                exits = "south ";
            }
            else{
                exits = exits + "south ";
            }
        }
        if(westExit != null) {
            if(exits == null){
                exits = "west ";
            }
            else{
                exits = exits + "west ";
            }
        }
        if(southEastExit != null) {
            if(exits == null){
                exits = "southEast ";
            }
            else{
                exits = exits + "southEast ";
            }
        }
        if(northEastExit != null) {
            if(exits == null){
                exits = "northEast ";
            }
            else{
                exits = exits + "northEast ";
            }
        }
        return exits;
    }
}
