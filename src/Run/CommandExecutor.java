package Run;

import Commands.*;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.HashMap;
import java.util.Scanner;

public class CommandExecutor {
    private CollectionManager collectionManager;

    private final HashMap<String, Command> commands;

    public CommandExecutor(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>();

        Command show = new Show(this.collectionManager);
        Command clear = new Clear(this.collectionManager);
        commands.put(show.getName(), show);
        commands.put(clear.getName(), clear);
    }

    public void enterInteractiveMode(){
        System.out.println("Interactive mode");
        Scanner commandReader = new Scanner(System.in);

        while (true){
            System.out.println("Enter a command");
            String[] line = commandReader.nextLine().toLowerCase().strip().split(" "); // read command from terminal

            if(!commands.containsKey(line[0])) {
                System.out.println("Not a command");
                continue;
            }

            try{
                Command command = commands.get(line[0]);
                command.execute(line);
            }
            catch (WrongArgument e){
                System.out.println(e.getMessage());
            }

        }
    }
}
