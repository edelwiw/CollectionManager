package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Utils.CLIManager;

import java.util.Iterator;

/**
 * Remove_by_id command. Removes all elements greater that spec.
 * This command uses collectionManager reference.
 */
public class RemoveGreater implements Command{

    private final CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {

        Iterator<Dragon> iter = collectionManager.getIterator();

        if(!iter.hasNext()) {
            System.out.println("Nothing to remove. Collection empty");
            return;
        }

        CLIManager cliManager = new CLIManager();
        Dragon dragon = new Dragon();
        cliManager.requestDragon(dragon);

        int counter = 0;

        while (iter.hasNext()){
            Dragon el = iter.next();
            if(el.compareTo(dragon) > 0) {
                collectionManager.removeById(el.getId());
                counter += 1;
            }
        }
        System.out.println(counter + " elements was removed");
    }

    @Override
    public String getDescription() {
        return "remove all elements greater that this";
    }
}
