package Commands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

public class Sort implements Command {
    CollectionManager collectionManager;

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