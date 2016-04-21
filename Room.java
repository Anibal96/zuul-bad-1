import java.util.HashMap;
import java.util.ArrayList;
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
    public final String description;
    public HashMap<String, Room> salidas;
    public ArrayList<Objeto> objetos;
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
        objetos = new ArrayList<>();
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
        String item;
        if(objetos.size() > 0){
            item = "This room have " + objetos.size() + " items use 'inspect' for see";
        }
        else{
            item = "This room dont have items";
        }
        return "You are " + description + "\n" + "Exits: " + getExitString() + "\n" + item;
    }
    
    /**
     * Metodo que permite a�adir items a la habitacion
     */
    public void addItem(String descripcion, int peso)
    {
        objetos.add(new Objeto(descripcion, peso));
    }
    
    /**
     * Metodo que muestra la descripcion y peso de los objetos de la sala
     */
    public void seeItems()
    {
        for(Objeto objeto : objetos){
            System.out.println(objeto.toString());
        }
    }
    
    /**
     * Metodo que elimina un objeto y lo devuelve
     * en caso de no encontrarlo devuelve null
     */
    public Objeto removeItem(String descripcionItem)
    {
        Objeto object = null;
        int cont = 0;
        for(Objeto objeto:objetos){
            if(objeto.getDescripcion().equals(descripcionItem)){
                object = objeto;
            }
            cont++;
        }
        return object;
    }
    
}
