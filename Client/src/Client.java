import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws NotEnoughArgs, IOException, ClassNotFoundException {
        String address = "localhost";

//        request port
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter port");
        int port;
        while (true) {
            try {
                String raw = scanner.next();
                port = Integer.parseInt(raw);
                break;
            } catch (NumberFormatException e){
                System.out.println("Wrong port value. Enter again");
            }
        }

        try {
            Connector connector = new Connector(address, port);
            CommandExecutor commandExecutor = new CommandExecutor(connector);
            commandExecutor.enterInteractiveMode();
        }  catch (WrongArgument e){
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (ConnectException e){
            System.out.println("Connection failed");
        }


    }
}
