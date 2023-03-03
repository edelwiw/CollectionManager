package Commands;

import Exceptions.WrongArgument;

public interface Command {
    public void execute(String[] args) throws WrongArgument;
    public String getDescription();
}
