package Run;

import Collection.Dragon;

import java.time.ZonedDateTime;
import java.util.LinkedList;

public class CollectionManager {

    private LinkedList<Dragon> dragons;
    private ZonedDateTime creationDate;

    public CollectionManager(){
        dragons = new LinkedList<>();
        this.creationDate = ZonedDateTime.now();
    }

    public void add_obj(Dragon obj){
        dragons.add(obj);
    }

    public void show() {
        if (dragons.size() == 0) {
            System.out.println("Nothing to show. Collection empty.");
            return;
        }
        for (int i = 0; i < this.dragons.size(); i++) {
            System.out.println("-----------------------");
            System.out.println(this.dragons.get(i).getName());
            System.out.println(this.dragons.get(i).toString());
        }
        System.out.println("-----------------------");
    }

}
