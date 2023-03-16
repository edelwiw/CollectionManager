package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;

import java.util.HashMap;

/**
 * Help command. Prints info about all available commands.
 * This command uses HashMap with all commands in program to get its names and descriptions.
 */
public class Help implements Command{

    private final HashMap<String, Command> commands;

    public Help(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        for(String command : this.commands.keySet()){
            System.out.println(command + "\t - " + this.commands.get(command).getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "shows all available commands (this command)";
    }
}
