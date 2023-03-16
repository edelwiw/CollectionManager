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

    public Path getPath(){
        return defaultPath;
    }

    public void add(Dragon dragon){
        dragons.add(dragon);
        System.out.println("Element added successfully");
    }

    public void clearCollection() {
        this.dragons.clear();
        System.out.println("Collection cleared successfully");
    }

    public void removeById(long id){
        for(int index = 0; index < dragons.size(); index++){
            if(dragons.get(index).getId() == id){
                this.removeByIndex(index);
                System.out.println("Object removed successfully");
                break;
            }
        }
        System.out.println("Nu such element");
    }

    public void removeByIndex(int index) throws IndexOutOfBoundsException{
        dragons.remove(index);
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

    public Stream<Dragon> getStream(){
        return dragons.stream();
    }

    public Dragon getByID(long id){
        for(int index = 0; index < dragons.size(); index++){
            if(dragons.get(index).getId() == id){
                return dragons.get(index);
            }
        }
        return null;
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


    public void fillCollectionFromFile(String filePath){
        try {
            // check filePath
            Path path = Paths.get(filePath);
            if(!Files.exists(path)) throw new FileNotFoundException();
            if(!Files.isReadable(path)) throw new NoPermissionException("Cannot read file.");
            if(!Files.isWritable(path)) throw new NoPermissionException("Cannot write to file.");

            // create BufferedInputStream and read bytes
            BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path));
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            CsvToBean<Dragon> csv = new CsvToBeanBuilder<Dragon>(reader).withType(Dragon.class).build();

            dragons.addAll(csv.parse());
        }
        catch (InvalidPathException e){
            System.out.println("Argument must be a correct file path.");
        }
        catch (FileNotFoundException e){
            System.out.println("File " + filePath + " not found. Data not loaded."); // file does not exist
        }
        catch (NoPermissionException e){
            System.out.print("No enough permissions to " + filePath + " - " + e.getMessage()); // permissions deny
            System.exit(1);
        }
        catch (IOException e){e.printStackTrace();}
    }


}
