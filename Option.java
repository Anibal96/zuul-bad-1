
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
     GO("go"), QUIT("quit"), HELP("help"),LOOK("look"),EAT("eat"),INSPECT("inspect"),BACK("back"),
     TAKE("take"),DROP("drop"),ITEMS("items"), USE("use"),UNKNOWN("");
     
     private String comando;
     
     /**
      * Constructor de la clase Option
      */
     private Option (String comando)
     {
         this.comando = comando;
     }
     
     /**
      * Metodo que devuelve un String que es el comando
      */
     public String getComando()
     {
         return comando;
     }
}
