import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;

import java.io.IOException;
import java.net.ConnectException;

public class Client {
    public static void main(String[] args) throws NotEnoughArgs, IOException, ClassNotFoundException {
        String address = "localhost";
        int port = 5454;

        try {
            Connector connector = new Connector(address, port);
            CommandExecutor commandExecutor = new CommandExecutor(connector);
            commandExecutor.enterInteractiveMode();
            connector.closeConnection();
        }  catch (WrongArgument e){
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (ConnectException e){
            System.out.println("Connection failed");
        }


    }
}
