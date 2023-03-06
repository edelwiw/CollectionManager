package Commands;

import Exceptions.WrongArgument;

/**
 * Base Command interface. Each command should implement this interface.
 */
public interface Command {
    /**
     * Execute command. Calls each time when command starts execution.
     * @param args String array with command arguments. First element should be command name.
     * @throws WrongArgument when arguments does not math requirements.
     */
    public void execute(String[] args) throws WrongArgument;

    /**
     * Get command description for "help" page.
     * @return command description string.
     */
    public String getDescription();
}
