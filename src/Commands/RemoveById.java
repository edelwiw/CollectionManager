package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.Optional;
import java.util.stream.Stream;


/**
 * Remove_by_id command. Deletes element with spec. id from collection.
 * This command uses collectionManager reference to call "RemoveById" method.
 */
public class RemoveById implements Command{

    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"id\" argument");
        long id;
        try{
            id = Long.parseLong(args[1]);
        }
        catch (NumberFormatException e){
           throw  new WrongArgument("Argument must be long integer number");
        }
        boolean result = collectionManager.removeById(id);
        if(result) System.out.println("Object removed successfully");
        else System.out.println("No such element");
    }

    @Override
    public String getDescription() {
        return "deletes element with spec. id from collection";
    }
}
