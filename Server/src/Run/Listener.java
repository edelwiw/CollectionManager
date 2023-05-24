package Run;

import ClientCommands.ClientCommand;
import Exceptions.WrongArgument;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Listener {
    private static final int BUFFER_SIZE = 4096;

    private final RequestHandler requestsHandler;
    private Selector selector = null;
    private ServerSocket serverSocket;

    /**
     * Constructor for listener. Creates server socket
     *
     * @param port server port
     */
    public Listener(int port, RequestHandler requestsHandler) throws WrongArgument, ConnectException {
        this.requestsHandler = requestsHandler;
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
        while (true) {
            try {
                int count = selector.select();
                if (count == 0) continue; // nothing to handle

                Set<SelectionKey> keySet = selector.selectedKeys(); // get keyset
                Iterator<SelectionKey> iterator = keySet.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next(); // get key and remove it from iterator
                    iterator.remove();

                    // key should be closed!

                    if (key.isAcceptable()) {
                        System.out.println("Got acceptable key");
                        // trying to connect
                        try {
                            Socket socket = serverSocket.accept();
                            System.out.println("Connected to " + socket.getRemoteSocketAddress());

                            SocketChannel socketChannel = socket.getChannel();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("Connection things done");

                        } catch (IOException e) {
                            System.out.println("Unable to accept channel!");
                            e.printStackTrace();
                            key.cancel();
                            continue;
                        }

                    }

                    if (key.isReadable()){
                        System.out.println("Gor readable key");
                        try (SocketChannel socketChannel = (SocketChannel) key.channel()){

                            ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE); // create buffer
                            socketChannel.read(buf); // read data
                            buf.flip();
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf.array());
                            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                            ClientCommand clientCommand = (ClientCommand) objectInputStream.readObject();
                            objectInputStream.close(); // close streams
                            byteArrayInputStream.close();
                            socketChannel.write(buf);

                            // TODO something with command


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
