import ClientCommands.*;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for interactive command execution mode.
 */
public class CommandExecutor {

    private final HashMap<String, Class<?>> commands;
    private final Connector connector;

    public CommandExecutor(Connector connector){
        this.commands = new HashMap<>();
        this.connector = connector;

        commands.put("show", Show.class);
        commands.put("add", Add.class);
        commands.put("group_counting_by_coordinates", GroupCountingByCoordinates.class);
        commands.put("info", Info.class);
        commands.put("filter_less_than_age", FilterLessThanAge.class);
        commands.put("head", Head.class);
        commands.put("clear", Clear.class);
        commands.put("help", Help.class);
        commands.put("update", Update.class);
        commands.put("add_if_min", AddIfMin.class);
        commands.put("remove_by_id", RemoveByID.class);
//        commands.put("execute_script", new ExecuteScript(this));

    }

    /**
     * Enter an interactive mode with remote commands execution.
     */
    public void enterInteractiveMode(){
        System.out.println("Interactive mode");
        Scanner commandReader = new Scanner(System.in);


        while (true){
            System.out.println("Enter a command"); // read command from terminal

            try{
                String[] argsArray = parseInput(commandReader.nextLine());

                Class<?> comm = getCommand(argsArray[0]); // TODO FIX create new instance

                if (comm == null){
                    System.out.println("Not a command. Try again.");
                    continue;
                }

                ClientCommand command = (ClientCommand) comm.newInstance();

                command.prepareRequest(argsArray);

                try{
                    Response response = this.connector.sendAndGetResponse(command);
                    command.handleResponse(response);
                } catch (ConnectException e){
                    System.out.println("Error while communication with server");
                    e.printStackTrace();
                }
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
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println("Error. Try again");
            }

        }
    }

    private Class getCommand(String commandName){
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

