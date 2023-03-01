package Commands;

import Exceptions.WrongArgument;
import Run.CollectionManager;

public abstract class Command {

    private final String name;
    private final String description;
    protected CollectionManager collectionManager;

    public Command(CollectionManager collectionManager, String name, String description){
        this.collectionManager = collectionManager;
        this.name = name;
        this.description = description;
    }
    public abstract void execute(String[] args) throws WrongArgument;

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }
}
