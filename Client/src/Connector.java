import ClientCommands.ClientCommand;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;
import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.ScrollPaneUI;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

/**
 * Class for communication with server throat TCP
 */
public class Connector {

    private final String address;
    private final int port;

    private InetAddress inetAddress;

    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    /**
     * Validate address and port
     * @param address server address
     * @param port server port
     * @throws WrongArgument when address or port is incorrect
     */
    public Connector(String address, int port) throws WrongArgument, ConnectException {
        this.address = address;
        this.port = port;

        try {
            if (port <= 1024 || port > 65535) throw new WrongArgument("Port value should be in range 1025-65535");
            this.inetAddress = InetAddress.getByName(address);

        } catch (UnknownHostException e){
            throw new WrongArgument("Unknown host");
        }
    }

    /**
     * Connect to host, get data streams
     * @throws ConnectException when cannot establish connection
     */
    private void connect() throws ConnectException{
        try {
            // try to connect to server
            this.socket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.inetAddress, this.port);
            this.socket.connect(inetSocketAddress, 1000);

            // get data streams
            this.outputStream = socket.getOutputStream();
            this.inputStream = socket.getInputStream();

            System.out.println("Connected");
        }  catch (IOException e) {
            throw new ConnectException("Failed to connect to server");
        }

    }

    /**
     * Read response from server
     * @return Response class with payload
     * @throws ConnectException when cannot establish connection
     */
    private Response readResponse() throws ConnectException{
        try {
            byte[] data = new byte[4096];

            this.inputStream.read(data);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Response response = (Response) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();

            return response;
        } catch (ClassNotFoundException | IOException | ClassCastException e){
            e.printStackTrace();
            throw new ConnectException("Cannot read response");
        }
    }

    /**
     * Sent request with command and args to server
     * @param command command to execute
     * @throws ConnectException when cannot establish connection
     * @see ClientCommand
     */
    private void sendRequest(ClientCommand command) throws ConnectException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            objectOutputStream.close();
            byteArrayOutputStream.close();

            this.outputStream.write(buffer.array());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectException("Cannot write request");
        }
    }

    public Response sendAndGetResponse(ClientCommand command) throws ConnectException{
        this.connect();
        this.sendRequest(command);
        Response response = this.readResponse();
        this.closeConnection();
        return response;
    }

    public void closeConnection(){
        try {
            this.socket.close();
        } catch (IOException e){
            System.out.println("Error while closing connection");
        }

    }

}
