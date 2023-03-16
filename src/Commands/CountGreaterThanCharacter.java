package Commands;

import Collection.Dragon;
import Collection.DragonCharacter;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Utils.CLIManager;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Count_greater_than_character command. Print number of elements with character greater than spec.
 * This command uses collectionManager reference.
 */
public class CountGreaterThanCharacter implements Command{

    private final CollectionManager collectionManager;

    public CountGreaterThanCharacter(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"Character\" argument");
        DragonCharacter character;
        try{
            character = DragonCharacter.valueOf(args[1].toUpperCase());
        }
        catch (IllegalArgumentException e){
            throw  new WrongArgument("Character must be selected from " + Arrays.toString(DragonCharacter.values()) + ".");
        }

        Iterator<Dragon> iter = collectionManager.getIterator();

        if(!iter.hasNext()) {
            System.out.println("Nothing to show. Collection empty.");
            return;
        }
        while (iter.hasNext()){
            Dragon dragon = iter.next();
            if(dragon.getCharacter() == null) continue;
            if(dragon.getCharacter().compareTo(character) > 0) {
                System.out.println("-----------------------");
                System.out.println(dragon.getName());
                System.out.println(dragon);
            }
        }
        System.out.println("-----------------------");

    }

    @Override
    public String getDescription() {
        return "print number of elements with character greater than spec.";
    }
}
