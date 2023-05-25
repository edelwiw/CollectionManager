package RemoteCommands;

import ClientCommands.ClientCommand;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class ClearServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public ClearServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        collectionManager.clearCollection();
        return new Response(ResponseCode.OK);
    }

    @Override
    public String getDescription() {
        return "clear all collection items";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
