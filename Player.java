
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }
    
    /**
     * Metodo que me permite moverte a otra habitacion
     */
    public void move(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Metodo que devuelve la habitacion en la que esta el jugador
     */
    public Room getRoom()
    {
        return currentRoom;
    }
}