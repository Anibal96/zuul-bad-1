import java.util.ArrayList;
import java.util.Stack;
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
    private Stack<Room> lastRooms;
    private float cargaActual;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom, float cargaMaxima)
    {
        this.currentRoom = currentRoom;
        this.cargaMaxima = cargaMaxima;
        cargaActual = 0f;
        lastRooms = new Stack<Room>();
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
     * Metodo que devuelve la ultima habitacione por la que paso el jugador.
     */
    public Room getLastRoom()
    {
        return lastRooms.pop();
    }
    
    /**
     * Metodo que comprueba si se puede retroceder de sala 
     * devuelve true en caso afirmativo y false en caso contrario
     */
    public boolean isEmpty()
    {
        return lastRooms.empty();
    }
    
    /**
     * Añade una habitacion a la lista de habitaciones visitadas
     */
    public void addLastRoom()
    {
        lastRooms.push(currentRoom);
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

    /**
     * Metodo que devuelve el objeto dropeade o
     * null en caso de no encontralo
     */
    public Objeto dropItem(String descripcion)
    {
        Objeto objeto = null;
        for(int cont = 0; cont < objetos.size() && objeto == null ; cont++){
            if(objetos.get(cont).getDescripcion().equals(descripcion)){
                objeto = objetos.remove(cont);
            }
        }
        return objeto;
    }

    /**
     * Metodo que imprime los datos de los objetos
     */
    public void infoItem(){
        int cont = 1;
        for(Objeto objeto : objetos){
            System.out.println("Item nº " + cont + "\n" + objeto);
            cont++;
        }
    }
}