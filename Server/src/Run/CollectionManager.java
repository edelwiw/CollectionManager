package Run;

import Collection.Dragon;
import Exceptions.WrongArgument;
import Exceptions.WrongField;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.naming.NoPermissionException;
import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * Class for work with collection.
 */
public class CollectionManager {

    private final DatabaseConnector databaseConnector;
    private final LinkedList<Dragon> dragons;
    private final ZonedDateTime creationDate;

    /**
     * Constructor. Creates abject to work with collection.
     */
    public CollectionManager(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
        dragons = new LinkedList<>();
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Adds object to collection
     * @param dragon object to add
     */
    public void add(Dragon dragon){
        try {
            int id = databaseConnector.addDragon(dragon);
            dragon.setId(id);
            dragons.add(dragon);
            this.sortCollection();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error while saving element");
        }

    }

    /**
     * Update dragon
     * @param dragon object to update
     */
    public void update(Dragon dragon){
        try{
            databaseConnector.updateDragon(dragon);

            for(int index = 0; index < dragons.size(); index++){
                if(dragons.get(index).getId() == dragon.getId()){
                    this.dragons.add(index, dragon);
                    break;
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error while updating element");
        }
    }

    /**
     * Removes all elements from collection
     */
    public void clearCollection() {
        try {
            databaseConnector.clearDatabase();
            this.dragons.clear();
        } catch (SQLException e){
            System.out.println("Error while clearing collection");
        }

    }

    /**
     * Removes object from collection with specified id.
     * @param id id of object to be removed from collection.
     * @return true if object was removed successfully, false if object with spec. id does now exist.
     */
    public boolean removeById(long id){
        for(int index = 0; index < dragons.size(); index++){
            if(dragons.get(index).getId() == id){
                this.removeByIndex(index);
                return true;
            }
        }
       return false;
    }

    /**
     * Removes element with specified index
     * @param index object to be removed index
     * @throws IndexOutOfBoundsException when elements with such index does not exist
     */
    public void removeByIndex(int index) throws IndexOutOfBoundsException{
        try {
            databaseConnector.removeDragonByID(dragons.get(index).getId());
            dragons.remove(index);
        } catch (SQLException e){
            System.out.println("Error while deleting");
        }

    }

    /**
     * Get info about collection
     * @return string with info about collection
     */
    public String getInfo(){
        String result = "";
        result += "Information about collection:\n";
        result += "Created at " + this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + '\n';
        result += "Collection type is " + this.dragons.getClass().getName() + '\n';
        result += "Amount of items stored in - " + this.dragons.size() + '\n';

        return result;
    }

    /**
     * Get iterator
     * @return iterator
     */
    public Iterator<Dragon> getIterator(){
        return dragons.iterator();
    }

    /**
     * Get stream
     * @return stream
     */
    public Stream<Dragon> getStream(){
        return dragons.stream();
    }

    /**
     * Get object with specified id from collection
     * @param id id of object to find
     * @return dragon object with spec. id
     */
    public Dragon getByID(long id){
        for(int index = 0; index < dragons.size(); index++){
            if(dragons.get(index).getId() == id){
                return dragons.get(index);
            }
        }
        return null;
    }

    /**
     * Get array with collection elements
      * @return array with collection elements
     */
    public Dragon[] getArray(){
        Dragon[] array = new Dragon[this.getSize()];
        this.dragons.toArray(array);
        return array;
    }


    /**
     * Sorts collection
     */
    public void sortCollection(){
        Collections.sort(dragons);
    }

    /**
     * Get element with min. value
     * @return dragon object with min. value
     */
    public Dragon getMin(){
        if(dragons.size() > 0) return Collections.min(dragons);
        return null;
    }

    /**
     * Get collection size
     * @return number of elements stored in collection
     */
    public int getSize(){
        return dragons.size();
    }

    public void readDatabase(){
        try {
            ArrayList<Integer> ids = databaseConnector.getDragonsIDs();
            for(int id : ids){
                Dragon dragon = databaseConnector.readDragon(id);
                dragons.add(dragon);
            }
            System.out.println(ids.size() + " elements read from database");
        } catch (SQLException e){
            System.out.println("Error while reading database");
        }
    }

}
