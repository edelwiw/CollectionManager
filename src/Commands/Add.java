package Commands;

import Collection.Dragon;
import Exceptions.WrongArgument;
import Utils.CLIManager;
import Utils.CollectionManager;

public class Add implements Command{

    CollectionManager collectionManager;

    public Add(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument {
        CLIManager cliManager = new CLIManager();
        Dragon dragon = cliManager.requestDragon();
        collectionManager.add(dragon);
    }

    @Override
    public String getDescription() {
        return "add item to collection. Program will request each field of collection object";
    }
}
