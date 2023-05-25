package RemoteCommands;

import ClientCommands.ClientCommand;
import Utils.Response;

/**
 * Base class for server command
 */
public interface ServerCommand {

    public Response execute(ClientCommand command);

    public String getDescription();

    public String getName();

}
