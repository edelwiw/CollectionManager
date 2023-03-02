package Commands;

import Exceptions.WrongArgument;

import java.util.HashMap;

public class Help implements Command{
    private final HashMap<String, Command> commands;

    public Help(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) throws WrongArgument {

    }

    @Override
    public String getDescription() {
        return "shows all available commands (this command)";
    }
}
