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
    private static final int NUM_INTENTOS = 100;
    private static final String DESCRIPCION_FUERA = "you're outside";

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room aula203, aula204, aula205, aula206, aula207, aula208, aula209, salida;

        // create the rooms
        aula203 = new Room("in the aula 203");
        aula203.addItem("Mapa", 0, false);
        aula203.addItem("Extintor", 5, true);
        aula204 = new Room("in the aula 204");
        aula205 = new Room("in the aula 205");
        aula206 = new Room("in the aula 206");
        aula207 = new Room("in the aula 207");
        aula207.addItem("Mesa", 20, false);
        aula208 = new Room("in the aula 208");
        aula209 = new Room("in the aula 209");
        salida = new Room("you're outside");

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
        while(!finished && cont < NUM_INTENTOS && !(player.getRoom().getDescription().equals(DESCRIPCION_FUERA))) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            cont++;
        }
        if(finished){
            System.out.println("you quit the game");
        }
        else if(player.getRoom().getDescription().equals(DESCRIPCION_FUERA)){
            System.out.println("you have saved");
        }
        else{
            System.out.println("YOU DIED");
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

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        Option commandWord = command.getCommandWord();
        if (commandWord.equals(Option.HELP)) {
            printHelp();
        }
        else if (commandWord.equals(Option.GO)) {
            goRoom(command);
        }
        else if (commandWord.equals(Option.QUIT)) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals(Option.LOOK)) {
            System.out.println(player.getRoom().getLongDescription());
        }
        else if (commandWord.equals(Option.EAT)) {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals(Option.INSPECT)) {
            player.getRoom().seeItems();
        }
        else if (commandWord.equals(Option.BACK)) {
            if(player.isEmpty()){
                System.out.println("You can't back or you dont move");
            }
            else{
                player.move(player.getLastRoom());
                System.out.println(player.getRoom().getLongDescription());
            }
        }
        else if(commandWord.equals(Option.TAKE)){
            takeItem(command.getSecondWord());
        }
        else if(commandWord.equals(Option.DROP)){
            dropItem(command.getSecondWord());
        }
        else if(commandWord.equals(Option.ITEMS)){
            player.infoItem();
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
        System.out.println("Type 'aiuto' if you need help.");
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
