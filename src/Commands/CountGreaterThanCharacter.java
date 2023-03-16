package Commands;

import Collection.Dragon;
import Collection.DragonCharacter;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Utils.CLIManager;

import javax.sql.rowset.Predicate;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

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

        Stream<Dragon> stream = collectionManager.getStream();

        System.out.println("Number of elements greater than " + character + " is " + stream.filter((x) -> x.getCharacter().compareTo(character) > 0).count());

    }

    @Override
    public String getDescription() {
        return "print number of elements with character greater than spec.";
    }
}
