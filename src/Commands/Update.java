package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Utils.CLIManager;

/**
 * Update command. Request element from CLI and add update element with spec. id
 * This command uses collectionManager reference..
 */
public class Update implements Command{

    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
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
            throw  new WrongArgument("Argument must be long integer number.");
        }

        Dragon dragon = collectionManager.getByID(id); // get object to update
        if(dragon == null) throw new WrongArgument("No such element.");

        CLIManager cliManager = new CLIManager();
        cliManager.requestDragon(dragon); // request new fields

    }

    @Override
    public String getDescription() {
        return "update element with spec. id";
    }
}
