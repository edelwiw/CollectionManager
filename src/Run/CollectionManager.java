package Run;

import Collection.Dragon;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Class for work with collection.
 */
public class CollectionManager {

    private final LinkedList<Dragon> dragons;
    private ZonedDateTime creationDate;

    /**
     * Constructor. Creates abject to work with collection.
     */
    public CollectionManager(){
        dragons = new LinkedList<>();
        this.creationDate = ZonedDateTime.now();
    }

    public void add(Dragon dragon){
        dragons.add(dragon);
        System.out.println("Element added successfully");
    }

    public void clearCollection() {
        this.dragons.clear();
        System.out.println("Collection cleared successfully");
    }

    public String getInfo(){
        String result = "";
        result += "Information about collection:\n";
        result += "Created at " + this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + '\n';
        result += "Collection type is " + this.dragons.getClass().getName() + '\n';
        result += "Amount of items stored in - " + this.dragons.size() + '\n';

        return result;
    }

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
