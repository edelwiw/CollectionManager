package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Clear command. Delete all items from collection.
 * This command uses collectionManager reference to call "clear" method.
 */
public class Clear implements Command{

    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        this.collectionManager.clearCollection();
        System.out.println("Collection cleared successfully");
    }

    @Override
    public String getDescription() {
        return "clear all collection items";
    }
}
