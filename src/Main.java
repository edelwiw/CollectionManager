// times crying while writing this code = 4

import Exceptions.NotEnoughArgs;
import Run.CollectionManager;
import Run.CommandExecutor;

import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NotEnoughArgs {
        if(args.length == 0) throw new NotEnoughArgs("No file path specified");
        CollectionManager dragons = new CollectionManager(Paths.get(args[0]));

        dragons.fillCollectionFromFile();

        CommandExecutor commandExecutor = new CommandExecutor(dragons);
        commandExecutor.enterInteractiveMode();
    }
}