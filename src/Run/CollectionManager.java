package Run;

import Collection.Dragon;
import Exceptions.WrongArgument;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javax.naming.NoPermissionException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * Class for work with collection.
 */
public class CollectionManager {

    private final Path defaultPath;
    private final LinkedList<Dragon> dragons;
    private ZonedDateTime creationDate;

    /**
     * Constructor. Creates abject to work with collection.
     */
    public CollectionManager(Path filePath){
        defaultPath = filePath;
        dragons = new LinkedList<>();
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Returns s default file path specified in class.
     * @return path
     */
    public Path getPath(){
        return defaultPath;
    }

    /**
     * Adds object to collection
     * @param dragon object to add
     */
    public void add(Dragon dragon){
        dragons.add(dragon);
    }

    /**
     * Removes all elements from collection
     */
    public void clearCollection() {
        this.dragons.clear();
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
        dragons.remove(index);
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

    /**
     * Fill collection from file from default file path
     */
    public void fillCollectionFromFile(){
        fillCollectionFromFile(defaultPath);
    }

    /**
     * Fill collection from file
     * @param path path to .csv file to load from
     */
    public void fillCollectionFromFile(Path path){
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path))){
            // check filePath;
            if(!Files.exists(path)) throw new FileNotFoundException("File " + path + " not found");
            if(!Files.isReadable(path)) throw new NoPermissionException("Cannot read file.");
            if(!Files.isWritable(path)) throw new NoPermissionException("Cannot write to file.");

            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            CsvToBean<Dragon> csv = new CsvToBeanBuilder<Dragon>(reader).withType(Dragon.class).build();

            try {
                dragons.addAll(csv.parse());
            }
            catch (Throwable e){
                System.out.println("An error occurred while reading file. Data not loaded.");
            }

            System.out.println(dragons.size() + " item(s) loaded from file " + path);
        }
        catch (InvalidPathException e){
            System.out.println("Argument must be a correct file path. Data not loaded.");
        }
        catch (FileNotFoundException e){
            System.out.println("File " + path + " not found. Data not loaded."); // file does not exist
        }
        catch (NoPermissionException e){
            System.out.print("No enough permissions to " + path + " - " + e.getMessage() + " Data not loaded."); // permissions deny
        }
        catch (IOException e){e.printStackTrace();}
    }


}
