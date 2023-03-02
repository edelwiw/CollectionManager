package Run;

import Commands.*;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.HashMap;
import java.util.Scanner;

public class CommandExecutor {

    private final CollectionManager collectionManager;

    private final HashMap<String, Command> commands;

    public CommandExecutor(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>();

        // TODO add commands automatically
        commands.put("help", new Help(this.commands));
        commands.put("show", new Show(this.collectionManager));
        commands.put("clear", new Clear(this.collectionManager));

    }

    public void enterInteractiveMode(){
        System.out.println("Interactive mode");
        Scanner commandReader = new Scanner(System.in);

        while (true){
            System.out.println("Enter a command");
            String[] line = commandReader.nextLine().toLowerCase().strip().split(" "); // read command from terminal

            if(!commands.containsKey(line[0])) { // check if command exist
                System.out.println("Not a command. Try again.");
                continue;
            }

            try{
                Command command = commands.get(line[0]);
                command.execute(line);
            }
            catch (WrongArgument e){
                System.out.println("Wrong argument! " + e.getMessage() + " Try again.");
            }

        }
    }
}
