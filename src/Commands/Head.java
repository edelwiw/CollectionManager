package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.Iterator;

/**
 * Group counting by coordinates command. Print first element of collection
 * This command uses collectionManager reference.
 */
public class Head implements Command{

    private final CollectionManager collectionManager;

    public Head(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        Iterator<Dragon> iter = collectionManager.getIterator();
        if(!iter.hasNext()){
            System.out.println("Nothing to show. Collection empty");
            return;
        }
        System.out.println(iter.next());
    }

    @Override
    public String getDescription() {
        return "print first element of collection";
    }
}
