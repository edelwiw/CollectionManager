package Commands;

import Exceptions.WrongArgument;
import Run.CollectionManager;

public class Clear extends Command{
    public Clear(CollectionManager collectionManager) {
        super(collectionManager, "clear", "delete all elements from collection");
    }

    @Override
    public void execute(String[] args) throws WrongArgument {
        collectionManager.clearCollection();
    }
}
