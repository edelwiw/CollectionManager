package Run;

import Commands.*;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;

import java.util.HashMap;
import java.util.Scanner;


/**
 * Class for interactive CLI mode.
 */
public class CommandExecutor {

    private final CollectionManager collectionManager;

    private final HashMap<String, Command> commands;

    /**
     * Constructor for command executor.
     * @param collectionManager collection manager class object
     */
    public CommandExecutor(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.commands = new HashMap<>();

        commands.put("help", new Help(this.commands));
        commands.put("show", new Show(this.collectionManager));
        commands.put("clear", new Clear(this.collectionManager));
        commands.put("add", new Add(this.collectionManager));
        commands.put("exit", new Exit());
        commands.put("info", new Info(this.collectionManager));
        // TODO update id {element}
        // TODO remove_by_id id
        // TODO save
        // TODO execute_script file_name
        // TODO head
        // TODO add_if_min {element}
        // TODO remove_greater {element}
        // TODO group_counting_by_coordinates
        // TODO count_greater_than_character character
        // TODO filter_less_than_age age

    }

    /**
     * Enter an interactive mode with CLI commands execution.
     */
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

            try{ // try to execute command with arguments
                Command command = commands.get(line[0]);
                command.execute(line);
            }
            catch (WrongArgument e){
                System.out.println("Wrong argument! " + e.getMessage() + " Try again.");
            }
            catch (NotEnoughArgs e){
                System.out.println("Not enough arguments. " + e.getMessage() + " Try again.");
            }
            // TODO add NotEnoughArguments exception

        }
    }
}
