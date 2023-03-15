package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Utils.CLIManager;

/**
 * Add_if_min command. Request element from CLI and add it to collection if their value less than minimum of collection.
 * This command uses collectionManager reference to call "add" method.
 */
public class AddIfMin implements Command {

    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        CLIManager cliManager = new CLIManager();
        Dragon dragon = cliManager.requestDragon();
        if(collectionManager.getSize() == 0) this.collectionManager.add(dragon);
        else if (dragon.compareTo(collectionManager.getMin()) < 0){
            this.collectionManager.add(dragon);
            System.out.println("Element added");
        }
        else {
            System.out.println("Element not added. It is greater than min of collection");
        }

    }

    @Override
    public String getDescription() {
        return "add new element to collection if their value less than minimum of collection";
    }
}
