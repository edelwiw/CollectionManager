package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.Iterator;

/**
 * Show command. Prints all collection elements.
 * This command uses collectionManager reference to call "show" method.
 */
public class Show implements Command{
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        Iterator<Dragon> iter = collectionManager.getIterator();
        if(!iter.hasNext()) {
            System.out.println("Nothing to show. Collection empty");
            return;
        }
        while (iter.hasNext()){
            Dragon dragon = iter.next();
            System.out.println("-----------------------");
            System.out.println(dragon.getName());
            System.out.println(dragon);
        }
        System.out.println("-----------------------");

        }

    @Override
    public String getDescription() {
        return "show all collection items";
    }
}
