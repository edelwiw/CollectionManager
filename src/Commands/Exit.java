package Commands;

import Exceptions.WrongArgument;

public class Exit implements Command{

    @Override
    public void execute(String[] args) throws WrongArgument {
        System.out.println("Program will exit now! Bye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
