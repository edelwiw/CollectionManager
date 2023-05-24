package RemoteCommands;

import ClientCommands.ClientCommand;
import ClientCommands.RemoveByID;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class RemoveByIDSever implements ServerCommand{

    private final CollectionManager collectionManager;

    public RemoveByIDSever(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        RemoveByID clientCommand = (RemoveByID) command;
        long id = clientCommand.getId();
        boolean result = collectionManager.removeById(id);

        Response response = new Response(ResponseCode.ERROR);
        if (result) response.setResponseCode(ResponseCode.OK);
        else response.setMessage("No such element");
        return response;

        // TODO fix command
    }

    @Override
    public String getDescription() {
        return "deletes element with spec. id from collection";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}
