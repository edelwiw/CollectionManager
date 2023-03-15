package Run;

import Collection.Dragon;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public Iterator<Dragon> getIterator(){
        return dragons.iterator();
    }

    public void sortCollection(){
        Collections.sort(dragons);
    }

    public Dragon getMin(){
        if(dragons.size() > 0) return Collections.min(dragons);
        return null;
    }

    public int getSize(){
        return dragons.size();
    }


}
