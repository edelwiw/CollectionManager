package Commands;

import Exceptions.WrongArgument;
import Run.CollectionManager;

public interface Command {
    public void execute(String[] args) throws WrongArgument;
    public String getDescription();
}
