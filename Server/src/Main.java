// times crying while writing this code = 4

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Commands.CommandExecutor;
import Run.DatabaseConnector;
import Run.Listener;
import Run.RequestHandler;

import java.net.ConnectException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotEnoughArgs, WrongArgument, ConnectException {

        // request port
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
            DatabaseConnector databse = new DatabaseConnector("jdbc:postgresql://127.0.0.1:5432/collection", "alexivanov", "");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace(); // TODO REMOVE
        }

        if(args.length == 0) throw new NotEnoughArgs("No file path specified");
        CollectionManager dragons = new CollectionManager(Paths.get(args[0]));
        dragons.fillCollectionFromFile();

//        dragons.fillCollectionFromFile();
//
//        CommandExecutor commandExecutor = new CommandExecutor(dragons);
//        commandExecutor.enterInteractiveMode();
        try {
            Listener listener = new Listener(port, dragons);
            listener.startListening();
        } catch (WrongArgument e){
            System.out.println(e.getMessage() + ". Stopped.");
        }

    }
}