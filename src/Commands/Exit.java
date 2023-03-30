package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;

/**
 * Exit command. Stops program execution without saving any data to file.
 */
public class Exit implements Command{

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        System.out.println("Program will exit now! Bye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "exit program without saving data";
    }
}
