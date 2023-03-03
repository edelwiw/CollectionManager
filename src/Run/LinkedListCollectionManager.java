package Run;

import Collection.Dragon;
import Exceptions.WrongField;
import Utils.CLIManager;
import Utils.CollectionManager;

import java.time.ZonedDateTime;
import java.util.LinkedList;

public class LinkedListCollectionManager implements CollectionManager {

    private final LinkedList<Dragon> dragons;
    private ZonedDateTime creationDate;

    /**
     * Constructor. Creates abject to work with collection.
     */
    public LinkedListCollectionManager(){
        dragons = new LinkedList<>();
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Get dragon by fields from CLI and add it to collection.
     * Method will ask each field. If input value is incorrect method will ask to enter it again.
     */
    public void add(){
        Dragon dragon = new Dragon();
        CLIManager cliManager = new CLIManager();

        // request name
        while (true) {
            try {
                System.out.println("Enter a name");
                dragon.setName(cliManager.requestString());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong name. " + e.getMessage() + ". Enter again");
            }
        }

        // request age
        while (true) {
            try {
                System.out.println("Enter an age");
                dragon.setAge(cliManager.requestLong());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong age. " + e.getMessage() + ". Enter again");
            } catch (NumberFormatException e) {
                System.out.println("Age should be long integer number. Enter again");
            }
        }

        // request description
        while (true) {
            try {
                System.out.println("Enter a description");
                dragon.setDescription(cliManager.requestString());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong description. " + e.getMessage() + ". Enter again");
            }
        }

        // request weight
        while (true) {
            try {
                System.out.println("Enter a weight");
                dragon.setWeight(cliManager.requestDouble());
                break;
            } catch (WrongField e) {
                System.out.println("Wrong weight. " + e.getMessage() + ". Enter again");
            } catch (NumberFormatException e) {
                System.out.println("Weight should be floating point number. Enter again");
            }
        }

        // request character
        while (true) {
            try {
                System.out.println("Select character from this options.");
                dragon.setCharacter(cliManager.requestCharacter());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Character should be selected from options. Enter again");
            }
        }

        // TODO request coordinates
        // TODO request killer

        this.dragons.add(dragon);
    }

    /**
     * Clears all elements from collection
     */
    public void clearCollection() {
        this.dragons.clear();
        System.out.println("Collection cleared successfully");
    }

    /**
     * Print all collection items
     */
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
