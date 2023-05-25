package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Info command. Prints information about collection.
 * This command uses collectionManager reference to call "getInfo" method.
 */
public class Info implements Command{

    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        System.out.println(collectionManager.getInfo());
    }

    @Override
    public String getDescription() {
        return "shows information about collection";
    }
}
