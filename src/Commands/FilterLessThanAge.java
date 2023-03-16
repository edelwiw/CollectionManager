package Commands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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

        if(collectionManager.getSize() == 0){
            System.out.println("Nothing to show. Collection empty.");
            return;
        }

        Stream<Dragon> stream = collectionManager.getStream();

        List<Dragon> filtered = stream.filter((x) -> x.getAge() < age).toList();

        if(filtered.size() == 0){
            System.out.println("Nothing to show. No such elements.");
            return;
        }

        for(Dragon element : filtered){
            if(element.getAge() < age) {
                System.out.println("-----------------------");
                System.out.println(element.getName());
                System.out.println(element);
            }
        }
        System.out.println("-----------------------");
    }

    @Override
    public String getDescription() {
        return "shows all elements with age greater than spec. value";
    }
}
