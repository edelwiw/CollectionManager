package RemoteCommands;


import ClientCommands.ClientCommand;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class HeadServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public HeadServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        Dragon[] array = this.collectionManager.getArray();
        if(array.length > 0) {
            Response response = new Response(ResponseCode.OK);
            response.setPayload(array[0]);
            return response;
        } else {
            Response response = new Response(ResponseCode.OK_WITH_MESSAGE);
            response.setMessage("Collection empty");
            return response;
        }
    }

    @Override
    public String getDescription() {
        return "print first element of collection";
    }

    @Override
    public String getName() {
        return "head";
    }
}
