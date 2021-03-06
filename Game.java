/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private int turnos;
    private static final String DESCRIPCION_FUERA = "you're outside";

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        turnos = 10;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room aula203, aula204, aula205, aula206, aula207, aula208, aula209, salida;

        // create the rooms
        aula203 = new Room("in the aula 203");
        aula204 = new Room("in the aula 204");
        aula205 = new Room("in the aula 205");
        aula206 = new Room("in the aula 206");
        aula207 = new Room("in the aula 207");
        aula208 = new Room("in the aula 208");
        aula209 = new Room("in the aula 209");
        salida = new Room("you're outside");
        
        aula205.addItem("Mesa", 20, false);
        aula206.addItem("Mapa", 0, true);
        aula203.addItem("Extintor", 5, true);
        
        // initialise room exits
        aula203.setExits("north", aula207);
        aula203.setExits("east", aula206);
        aula203.setExits("west", aula204);
        aula204.setExits("north", aula205);
        aula204.setExits("east", aula203);
        aula205.setExits("south", aula204);
        aula205.setExits("southEast", aula203);
        aula206.setExits("west", aula203);
        aula207.setExits("north", aula208);
        aula207.setExits("east", salida);
        aula207.setExits("south", aula203);
        aula207.setExits("northEast", aula209);
        aula208.setExits("east", aula209);
        aula208.setExits("south", aula207);
        aula209.setExits("west", aula208);
        salida.setExits("west", aula207);

        player = new Player(aula203,15f);  // start game aula203
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        int cont = 0;
        while(!finished && cont < turnos && !(player.getRoom().getDescription().equals(DESCRIPCION_FUERA))) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            cont++;
            if((turnos-cont)>0){
                System.out.println("Te quedan " + (turnos-cont) + " turnos");
            }
        }
        if(finished){
            System.out.println("you quit the game");
        }
        else if(player.getRoom().getDescription().equals(DESCRIPCION_FUERA)){
            System.out.println("you have saved");
        }
        else{
            System.out.println("YOU DIE");
        }
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        Option commandWord = command.getCommandWord();
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        switch(command.getCommandWord()){
            case GO:
            goRoom(command);
            break;
            case QUIT:
            wantToQuit = quit(command);
            break;
            case HELP:
            printHelp();
            break;
            case LOOK:
            System.out.println(player.getRoom().getLongDescription());
            break;
            case EAT:
            System.out.println("Acabas de comer y no estaras hambriento en mucho tiempo");
            break;
            case BACK:
            if(player.isEmpty()){
                System.out.println("You can't back or you dont move");
            }
            else{
                player.move(player.getLastRoom());
                System.out.println(player.getRoom().getLongDescription());
            }
            break;
            case INSPECT:
            player.getRoom().seeItems();
            break;
            case TAKE:
            takeItem(command.getSecondWord());
            break;
            case DROP:
            dropItem(command.getSecondWord());
            break;
            case ITEMS:
            player.infoItem();
            break;
            case USE:
            if(player.inspectItem(command.getSecondWord()).getDescripcion().equals("Extintor")){
                if(turnos == 10){
                    turnos = turnos + 5;
                    System.out.println("You have 5 turns more for use 'Extintor'");
                }
                else{
                    System.out.println("the 'Extintor' is empty");
                }
            }
            else if(player.inspectItem(command.getSecondWord()).getDescripcion().equals("Mapa")){
                if(player.getRoom().getDescription().equals("in the aula 205")){
                    System.out.println("You can exit east of aula207");
                }
                else{
                    System.out.println("This room don't have a table");
                }
            }
            else{
                System.out.println("You dont have ");
            }
            break;
        }

        return wantToQuit;

    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.getCommandWords();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + Option.HELP.getComando() + "' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        player.addLastRoom();
        Room room = player.getRoom().getExit(direction);

        if (room== null) {
            System.out.println("There is no door!");
        }
        else {
            player.move(room);
            System.out.println(player.getRoom().getLongDescription());
        }
    }

    /**
     * Metodo que entrega el objeto al jugador
     */
    private void takeItem(String descripcionItem)
    {
        Objeto objeto = player.getRoom().inspectItem(descripcionItem);
        if(objeto == null){
            System.out.println("item not found");
        }
        else if (objeto.getPeso()+player.getCargaActual()>player.getCargaMaxima()){
            System.out.println("the object is too heavy");
        }
        else{
            if(objeto.getTake()){
                player.addItem(objeto);
                System.out.println("You take " + objeto.getDescripcion());
                player.getRoom().deleteItem(descripcionItem);
            }
            else{
                System.out.println("You can't take this item");
            }
        }

    }

    /**
     * Metodo que deja el objeto dropeado
     */
    private void dropItem(String descripcionItem)
    {
        Objeto object = player.dropItem(descripcionItem);
        if(object!=null){
            player.getRoom().addItem(object.getDescripcion(),object.getPeso(),true);
            System.out.println("You drop " + object.getDescripcion() + " in " + player.getRoom().getDescription());
        }
        else{
            System.out.println("item not found");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
