package Commands;

import Collection.Dragon;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Utils.CLIManager;

/**
 * Add command. Request element from CLI and add it to collection.
 * This command uses collectionManager reference to call "add" method.
 */
public class Add implements Command{

    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument {
        CLIManager cliManager = new CLIManager();
        Dragon dragon = cliManager.requestDragon();
        this.collectionManager.add(dragon);
    }

    @Override
    public String getDescription() {
        return "add item to collection. Program will request each field of collection object";
    }
}
