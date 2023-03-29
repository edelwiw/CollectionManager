package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import java.util.*;
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
        commands.put("filter_less_than_age", new FilterLessThanAge(this.collectionManager));
        commands.put("update", new Update(this.collectionManager));
        commands.put("count_greater_than_character", new CountGreaterThanCharacter(this.collectionManager));
        commands.put("group_counting_by_coordinates", new GroupCountingByCoordinates(this.collectionManager));
        commands.put("save", new Save(this.collectionManager));
        commands.put("execute_script", new ExecuteScript(this));

    }

    /**
     * Enter an interactive mode with CLI commands execution.
     */
    public void enterInteractiveMode(){
        System.out.println("Interactive mode");
        Scanner commandReader = new Scanner(System.in);

        while (true){
            System.out.println("Enter a command"); // read command from terminal

            try{
                String[] argsArray = parseInput(commandReader.nextLine());

                Command command = getCommand(argsArray[0]);

                if (command == null){
                    System.out.println("Not a command. Try again.");
                    continue;
                }
                // try to execute command with arguments
                command.execute(argsArray);
            }
            catch (WrongArgument e){
                System.out.println("Wrong argument! " + e.getMessage() + " Try again.");
            }
            catch (NotEnoughArgs e){
                System.out.println("Not enough arguments. " + e.getMessage() + " Try again.");
            }
            catch (NoSuchElementException e){
                System.out.println("Exit command");
                return;
            }

        }
    }

    Command getCommand(String commandName){
        if(!commands.containsKey(commandName)) return null; // check if command exist
        return commands.get(commandName);
    }

    String[] parseInput(String raw){
        if(raw.length() == 0) return new String[]{""};
        Matcher mather = Pattern.compile("[^\" ]+|\"[^\"]*\"").matcher(raw);

        ArrayList<String> line = new ArrayList<>();
        while (mather.find()) {line.add(mather.group().replaceAll("\"", ""));} // split arguments with regEx

        String[] argsArray = new String[line.size()]; // convert to String array
        argsArray = line.toArray(argsArray);
        argsArray[0] = argsArray[0].toLowerCase(); // command name to lover
        return argsArray;
    }
}
