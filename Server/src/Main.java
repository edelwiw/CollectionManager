// times crying while writing this code = 4

import Collection.*;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Run.CollectionManager;
import Commands.CommandExecutor;
import Run.DatabaseConnector;
import Run.Listener;
import Run.RequestHandler;
import Utils.User;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.net.ConnectException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotEnoughArgs, ConnectException, SQLException {

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

        DatabaseConnector database = null;

        try {
            database = new DatabaseConnector("jdbc:postgresql://127.0.0.1:5432/collection", "alexivanov", "");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace(); // TODO REMOVE
        }

        CollectionManager dragons = new CollectionManager(database);
        dragons.readDatabase();
//
        try {
            Listener listener = new Listener(port, dragons);
            listener.startListening();
        } catch (WrongArgument e){
            System.out.println(e.getMessage() + ". Stopped.");
        }

    }
}