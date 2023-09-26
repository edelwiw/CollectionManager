package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Exit command. Stops program execution without saving any data to file.
 */
public class Exit implements Command{

    private final CollectionManager collectionManager;

    public Exit(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
//        this.collectionManager.save();
        System.out.println("Program will exit now! Bye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "exit program without saving data";
    }
}
