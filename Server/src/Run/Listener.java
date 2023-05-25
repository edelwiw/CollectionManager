package Run;

import ClientCommands.ClientCommand;
import Commands.Command;
import Commands.CommandExecutor;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;

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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listener {
    private static final int BUFFER_SIZE = 4096;

    private final RequestHandler requestsHandler;
    private Selector selector = null;
    private ServerSocket serverSocket;
    private CollectionManager collectionManager;

    /**
     * Constructor for listener. Creates server socket
     *
     * @param port server port
     */
    public Listener(int port, CollectionManager collectionManager) throws WrongArgument, ConnectException {
        this.requestsHandler = new RequestHandler(collectionManager);
        this.collectionManager = collectionManager;
        ServerSocket serverSocket = null;

        try {
            // check for port value
            if (port <= 1024 || port > 65535) throw new IllegalArgumentException("Wrong port");

            // create server socket
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocket = serverSocketChannel.socket();

            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocket.bind(inetSocketAddress);
            this.serverSocket = serverSocket;

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
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

        while (true) {
            try {
                int count = selector.select(100);
                if (count == 0) { // nothing to handle
                    if(System.in.available() > 0){
                        try{
                            String[] argsArray = commandExecutor.parseInput(scanner.nextLine());

                            Command command = commandExecutor.getCommand(argsArray[0]);

                            if (command == null){
                                System.out.println("Not a command. Try again.");
                                continue;
                            }
                            // try to execute command with arguments
                            command.execute(argsArray);
                        }
                        catch (WrongArgument e){
                            System.out.println("Wrong argument! " + e.getMessage() + " Try again.");
                        }
                        catch (NotEnoughArgs e){
                            System.out.println("Not enough arguments. " + e.getMessage() + " Try again.");
                        }
                        catch (NoSuchElementException e){
                            System.out.println("Exit command");
                            return;
                        }
                    }
                }

                Set<SelectionKey> keySet = selector.selectedKeys(); // get keyset
                Iterator<SelectionKey> iterator = keySet.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next(); // get key and remove it from iterator
                    iterator.remove();

                    // key should be closed!

                    if (key.isAcceptable()) {
                        // trying to connect
                        try {
                            Socket socket = serverSocket.accept();
                            System.out.println("Connected to " + socket.getRemoteSocketAddress());

                            SocketChannel socketChannel = socket.getChannel();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);

                        } catch (IOException e) {
                            System.out.println("Unable to accept channel!");
                            e.printStackTrace();
                            key.cancel();
                            continue;
                        }

                    }

                    if (key.isReadable()){
                        System.out.println("Got readable key");
                        try (SocketChannel socketChannel = (SocketChannel) key.channel()){

                            ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE); // create buffer
                            socketChannel.read(buf); // read data
                            buf.flip();
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf.array());
                            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                            ClientCommand clientCommand = (ClientCommand) objectInputStream.readObject();
                            objectInputStream.close(); // close streams
                            byteArrayInputStream.close();

                            System.out.println("Got request");

                            Response response = requestsHandler.executeCommand(clientCommand);

                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                            objectOutputStream.writeObject(response);
                            objectOutputStream.flush();
                            buf.clear();
                            buf = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
                            objectOutputStream.close();
                            byteArrayOutputStream.close();

                            socketChannel.write(buf); // send response

                            System.out.println("Response sent");

                        } catch (IOException e){
                            e.printStackTrace();
                            key.cancel();
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            } catch (IOException e){
                e.printStackTrace(); // TODO
            }
        }
    }

}
