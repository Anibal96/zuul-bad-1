
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
    /**
     * Constructor for objects of class Objeto
     */
    public Objeto(String descripcion, int peso)
    {
        this.descripcion = descripcion;
        this.peso = peso;
    }

    /**
     * Metodo que devuelve el valor del peso del objeto
     */
    public int getPeso()
    {
        return peso;
    }
    
    /**
     * Metodo que devuelve la descripcion del objeto
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * metodo para imprimir los datos del objeto
     */
    public String toString()
    {
        return ("Descripcion: " + descripcion + "\n" + "Peso: " + peso);
    }
}
