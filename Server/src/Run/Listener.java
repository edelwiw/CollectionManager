package Run;

import ClientCommands.ClientCommand;
import Commands.Command;
import Commands.CommandExecutor;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserAuthStatus;
import Utils.UserData;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listener {
    private static final int BUFFER_SIZE = 4096;

    private final RequestHandler requestsHandler;
    private ServerSocket serverSocket;
    private CollectionManager collectionManager;
    private UserAuth userAuth;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    /**
     * Constructor for listener. Creates server socket
     *
     * @param port server port
     */
    public Listener(int port, CollectionManager collectionManager, UserAuth userAuth) throws WrongArgument, ConnectException {
        this.requestsHandler = new RequestHandler(collectionManager);
        this.collectionManager = collectionManager;
        this.userAuth = userAuth;
        ServerSocket serverSocket = null;

        try {
            // check for port value
            if (port <= 1024 || port > 65535) throw new IllegalArgumentException("Wrong port");

            // create server socket
            serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocket.bind(inetSocketAddress);
            this.serverSocket = serverSocket;

            System.out.println("Prepare done. " + "Server opened at port " + port);

        } catch (IllegalArgumentException e) {
            throw new WrongArgument("Port should be a value from 1024 to 65535");
        } catch (SecurityException e) {
            throw new ConnectException("Security error");
        } catch (IOException e) {
            System.err.println("Unable to setup environment");
            throw new ConnectException("Error while connecting");
        }

    }

    public void startListening() {
        CommandExecutor commandExecutor = new CommandExecutor(collectionManager);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command");

        // for listening CLI
        new Thread(() -> {
            while (true) {
                try {
                    String[] argsArray = commandExecutor.parseInput(scanner.nextLine());

                    Command command = commandExecutor.getCommand(argsArray[0]);

                    if (command == null) {
                        System.out.println("Not a command. Try again.");
                        continue;
                    }
                    // try to execute command with arguments
                    command.execute(argsArray);
                } catch (WrongArgument e) {
                    System.out.println("Wrong argument! " + e.getMessage() + " Try again.");
                } catch (NotEnoughArgs e) {
                    System.out.println("Not enough arguments. " + e.getMessage() + " Try again.");
                } catch (NoSuchElementException e) {
                    System.out.println("Exit command");
                    return;
                }
            }
        }
        ).start();


        executorService.submit(() -> {
            while (true) {

                Socket socket;
                ObjectInputStream objectInputStream;
                ObjectOutputStream objectOutputStream;

                socket = serverSocket.accept();
                System.out.println("Accepted");
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                Object request = objectInputStream.readObject();

                new Thread(() -> {
                    Response response = new Response(ResponseCode.ERROR);
                    if (request instanceof ClientCommand clientCommand) {
                        // execute command
                        response = requestsHandler.executeCommand(clientCommand);
                    } else if (request instanceof UserData) {
                        try {
                            // user auth request
                            if (!userAuth.isUserExist((UserData) request) && !((UserData) request).isToSignUp()){
                                response = new Response(ResponseCode.OK);
                                response.setPayload(UserAuthStatus.USER_NOT_EXIST);
                            } else if (!userAuth.isUserExist((UserData) request) && ((UserData) request).isToSignUp()) {
                                userAuth.signUp((UserData) request);
                                response = new Response(ResponseCode.OK);
                                response.setPayload(UserAuthStatus.SUCCESS);
                            } else if (userAuth.auth((UserData) request)){
                                response = new Response(ResponseCode.OK);
                                response.setPayload(UserAuthStatus.SUCCESS);
                            } else {
                                response = new Response(ResponseCode.OK);
                                response.setPayload(UserAuthStatus.WRONG_PASS);
                            }
                        } catch (SQLException e) {
                            response.setResponseCode(ResponseCode.ERROR);
                        }
                    }

                    Response finalResponse = response;
                    new Thread(() -> {
                        try {
                            objectOutputStream.writeObject(finalResponse);
                            objectOutputStream.flush();
                            objectOutputStream.close();
                            objectInputStream.close(); // close stream
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                }).start();
            }
        });

    }
}
