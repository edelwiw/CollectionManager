package Commands;

import Collection.Coordinates;
import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Group_Counting_By_Coordinates command. Group elements by character value and print count in each group.
 * This command uses collectionManager reference.
 */
public class GroupCountingByCoordinates implements Command{

    private final CollectionManager collectionManager;

    public GroupCountingByCoordinates(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws WrongArgument, NotEnoughArgs {

        if(collectionManager.getSize() == 0) {
            System.out.println("Collection is empty. Nothing to group.");
            return;
        }

        Map<Coordinates, List<Dragon>> grouped = collectionManager.getStream().collect(Collectors.groupingBy(Dragon::getCoordinates)); // goup

        System.out.println("Items count:");
        for(Coordinates entry : grouped.keySet()){
            System.out.println(entry + " - " + grouped.get(entry).size());
        }
    }

    @Override
    public String getDescription() {
        return "group elements by character value and print count in each group";
    }
}
