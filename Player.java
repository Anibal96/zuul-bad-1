import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Objeto> objetos;
    private static float cargaMaxima;
    private float cargaActual;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom, float cargaMaxima)
    {
        this.currentRoom = currentRoom;
        this.cargaMaxima = cargaMaxima;
        cargaActual = 0f;
        objetos = new ArrayList<Objeto>();
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

    /**
     * Metodo que permite cojer un objeto
     */
    public void addItem(Objeto objeto)
    {
        if(cargaActual + objeto.getPeso() < cargaMaxima){
            objetos.add(objeto);
            cargaActual += objeto.getPeso();
        }
        else{
            System.out.println("you can't grab this object");
        }
    }
}