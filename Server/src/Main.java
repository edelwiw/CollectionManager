// times crying while writing this code = 4

import Collection.*;
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

        DatabaseConnector database = null;

        try {
            database = new DatabaseConnector("jdbc:postgresql://127.0.0.1:5432/collection", "alexivanov", "");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace(); // TODO REMOVE
        }

        Location location = new Location();
        location.setX(13f);
        location.setY(123);
        Person person = new Person();
        person.setHairColor(Color.BLACK);
        person.setName("asd");
        person.setPassportID("123123123");
        person.setLocation(location);

        Dragon dragon = new Dragon();
        dragon.setKiller(person);
        dragon.setWeight(123.123);
        dragon.setCharacter(DragonCharacter.WISE);
        dragon.setAge(3123123L);
        dragon.setDescription("asdasd");
        Coordinates coordinates = new Coordinates();
        coordinates.setY(123123L);
        coordinates.setX(123.133);
        dragon.setCoordinates(coordinates);
        try {
            int id = database.addDragon(dragon);
            System.out.println(database.readDragon(id));

        } catch (SQLException e){
            e.printStackTrace();
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