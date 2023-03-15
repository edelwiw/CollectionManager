// times crying while writing this code = 0

import Collection.Dragon;
import Exceptions.WrongField;
import Run.CollectionManager;
import Run.CommandExecutor;

public class Main {
    public static void main(String[] args){
        CollectionManager dragons = new CollectionManager();

        Dragon dragon = new Dragon();
        dragons.add(dragon);
        System.out.println(dragon);

        CommandExecutor commandExecutor = new CommandExecutor(dragons);
        commandExecutor.enterInteractiveMode();
    }
}