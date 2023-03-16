package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.Iterator;

/**
 * Filter_less_than_age command. Prints all collection elements that less than spec. value.
 * This command uses collectionManager reference.
 */
public class FilterLessThanAge implements Command{

    private final CollectionManager collectionManager;

    public FilterLessThanAge(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"age\" argument");
        long age;
        try{
            age = Long.parseLong(args[1]);
        }
        catch (NumberFormatException e){
            throw  new WrongArgument("Argument must be long integer number.");
        }

        Iterator<Dragon> iter = collectionManager.getIterator();

        if(!iter.hasNext()) {
            System.out.println("Nothing to show. Collection empty.");
            return;
        }
        while (iter.hasNext()){
            Dragon dragon = iter.next();
            if(dragon.getAge() < age) {
                System.out.println("-----------------------");
                System.out.println(dragon.getName());
                System.out.println(dragon);
            }
        }
        System.out.println("-----------------------");
    }

    @Override
    public String getDescription() {
        return "shows all elements with age greater than spec. value";
    }
}
