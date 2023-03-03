// times crying while writing this code = 0

import Collection.Dragon;
import Run.LinkedListCollectionManager;
import Run.CommandExecutor;
import Utils.CollectionManager;

public class Main {
    public static void main(String[] args) {
        CollectionManager dragons = new LinkedListCollectionManager();

        Dragon dragon = new Dragon();
        System.out.println(dragon);

        CommandExecutor commandExecutor = new CommandExecutor(dragons);
        commandExecutor.enterInteractiveMode();
    }
}