package Run;

import Commands.*;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        commands.put("head", new Head(this.collectionManager));
        commands.put("sort", new Sort(this.collectionManager));
        commands.put("add_if_min", new AddIfMin(this.collectionManager));
        commands.put("remove_by_id", new RemoveById(this.collectionManager));
        commands.put("remove_greater", new RemoveGreater(this.collectionManager));
        // TODO update id {element}
        // TODO save
        // TODO execute_script file_name
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
            System.out.println("Enter a command"); // read command from terminal
            Matcher mather = Pattern.compile("[\\wа-яА-Я]+|\"[\\w\\sа-яА-Я]*\"").matcher(commandReader.nextLine());

            ArrayList<String> line = new ArrayList<>();
            while (mather.find()) {line.add(mather.group().replaceAll("\"", ""));} // split arguments with regEx

            String[] argsArray = new String[line.size()]; // convert to String array
            argsArray = line.toArray(argsArray);

            System.out.println(Arrays.toString(argsArray));

            if(!commands.containsKey(argsArray[0])) { // check if command exist
                System.out.println("Not a command. Try again.");
                continue;
            }

            try{ // try to execute command with arguments
                Command command = commands.get(argsArray[0]);
                command.execute(argsArray);
            }
            catch (WrongArgument e){
                System.out.println("Wrong argument! " + e.getMessage() + " Try again.");
            }
            catch (NotEnoughArgs e){
                System.out.println("Not enough arguments. " + e.getMessage() + " Try again.");
            }

        }
    }
}
