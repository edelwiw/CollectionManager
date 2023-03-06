package Commands;

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
    public void execute(String[] args) throws WrongArgument {
        this.collectionManager.clearCollection();
    }

    @Override
    public String getDescription() {
        return "clear all collection items";
    }
}
