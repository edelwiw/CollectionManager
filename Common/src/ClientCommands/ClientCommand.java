package ClientCommands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;

import java.io.*;


/**
 * Interface for client commands. Each object should contain all context for command execution.
 */
public interface ClientCommand extends Serializable {

    /**
     * On server command processing. Prepare objects to send to server.
     * @param args command args. First argument is command name.
     * @throws WrongArgument when arguments does not math requirements.
     * @throws NotEnoughArgs when argument count does not math requirements.
     */
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs;

    public void handleResponse(Response response);

}
