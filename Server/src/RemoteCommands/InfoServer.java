package RemoteCommands;


import ClientCommands.ClientCommand;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class InfoServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public InfoServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        Response response = new Response(ResponseCode.OK);
        response.setPayload(this.collectionManager.getInfo());
        return response;
    }

    @Override
    public String getDescription() {
        return "shows information about collection";
    }

    @Override
    public String getName() {
        return "info";
    }
}
