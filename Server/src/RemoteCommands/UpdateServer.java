package RemoteCommands;

import ClientCommands.ClientCommand;
import ClientCommands.Update;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class UpdateServer implements ServerCommand {

    private final CollectionManager collectionManager;

    public UpdateServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        Update clientCommand = (Update) command;
        Dragon dragon = clientCommand.getDragon();
        int id = clientCommand.getId();
        if(collectionManager.getByID(id) == null) {
            Response response = new Response(ResponseCode.ERROR);
            response.setMessage("No element with such id");
            return response;
        }
        dragon.setId(id);
        this.collectionManager.update(dragon);
        return new Response(ResponseCode.OK);
    }

    @Override
    public String getDescription() {
        return "update element with spec. id";
    }

    @Override
    public String getName() {
        return "update";
    }
}
