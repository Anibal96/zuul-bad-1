
/**
 * Write a description of class Objeto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objeto
{
    private int peso;
    private String descripcion;
    private boolean take;
    /**
     * Constructor for objects of class Objeto
     */
    public Objeto(String descripcion, int peso, boolean take)
    {
        this.descripcion = descripcion;
        this.peso = peso;
        this.take = take;
    }

    /**
     * Metodo que devuelve el valor del peso del objeto
     */
    public int getPeso()
    {
        return peso;
    }
    
    /**
     * Metodo que devuelve si el objeto se puede cojer o no
     */
    public boolean getTake()
    {
        return take;
    }
    
    /**
     * Metodo que devuelve la descripcion del objeto
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Metodo que usa el objeto
     */
    public void useObject()
    {
        
    }
    /**
     * metodo para imprimir los datos del objeto
     */
    public String toString()
    {
        return ("Descripcion: " + descripcion + "\n" + "Peso: " + peso);
    }
}
