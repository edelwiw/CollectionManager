// times crying while writing this code = 0

import Collection.Coordinates;
import Collection.Dragon;
import Run.CollectionManager;
import Run.CommandExecutor;

public class Main {
    public static void main(String[] args) {
        CollectionManager dragons = new CollectionManager();
//      dragons.readCollectionFromFile("/Users/alexivanov/Desktop/UNI/Java/Lab5/Dragons.xml");

        Dragon dragon1 = new Dragon();
        Dragon dragon2 = new Dragon();
        Dragon dragon3 = new Dragon();
        Dragon dragon4 = new Dragon();


        dragons.add_obj(dragon1);
        dragons.add_obj(dragon2);
        dragons.add_obj(dragon3);
        dragons.add_obj(dragon4);


        CommandExecutor commandExecutor = new CommandExecutor(dragons);
        commandExecutor.enterInteractiveMode();
    }
}