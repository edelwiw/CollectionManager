package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

/**
 * Sort command. Sorts all collection items by calling .sortCollection method in collection manager
 * This command uses collectionManager reference to call "sortCollection" method.
 */
public class Sort implements Command {
    private final CollectionManager collectionManager;

    public Sort(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        collectionManager.sortCollection();
    }

    @Override
    public String getDescription() {
        return "sorts collection";
    }
}
