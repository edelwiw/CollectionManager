package Run;

import Collection.Dragon;
import Utils.CollectionManager;

import java.time.ZonedDateTime;
import java.util.LinkedList;

/**
 * Class for work with collection.
 */
public class LinkedListCollectionManager implements CollectionManager<Dragon> {

    private final LinkedList<Dragon> dragons;
    private ZonedDateTime creationDate;

    /**
     * Constructor. Creates abject to work with collection.
     */
    public LinkedListCollectionManager(){
        dragons = new LinkedList<>();
        this.creationDate = ZonedDateTime.now();
    }

    @Override
    public void add(Dragon dragon){
        dragons.add(dragon);
        System.out.println("Element added successfully");
    }

    @Override
    public void clearCollection() {
        this.dragons.clear();
        System.out.println("Collection cleared successfully");
    }

    @Override
    public void show() {
        if (dragons.size() == 0) {
            System.out.println("Nothing to show. Collection empty");
            return;
        }
        for (Dragon dragon : this.dragons) {
            System.out.println("-----------------------");
            System.out.println(dragon.getName());
            System.out.println(dragon);
        }
        System.out.println("-----------------------");
    }

}
