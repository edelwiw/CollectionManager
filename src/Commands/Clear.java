package Commands;

import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Clear command. Delete all items from collection.
 */
public class Clear implements Command{

    CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument {
        collectionManager.clearCollection();
    }

    @Override
    public String getDescription() {
        return "clear all collection items";
    }
}
