package RemoteCommands;

import ClientCommands.ClientCommand;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

/**
 * Show server command. Prints all collection elements.
 */
public class ShowServer implements ServerCommand{
    private final CollectionManager collectionManager;

    public ShowServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        Dragon[] array = this.collectionManager.getArray();
        Response response = new Response(ResponseCode.OK);
        response.setPayload(array);
        return response;
    }

    @Override
    public String getDescription() {
        return "show all collection items";
    }

    @Override
    public String getName() {
        return "show";
    }

}
