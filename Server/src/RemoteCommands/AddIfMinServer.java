package RemoteCommands;

import ClientCommands.AddIfMin;
import ClientCommands.ClientCommand;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class AddIfMinServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public AddIfMinServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {

        AddIfMin clientCommand = (AddIfMin) command;
        Dragon dragon = clientCommand.getDragon();
        dragon.setCreatedBy(command.getUser().getId());
        if(collectionManager.getSize() == 0) {
            this.collectionManager.add(dragon);
            this.collectionManager.clearCollection();
            return new Response(ResponseCode.OK);
        }
        else if (dragon.compareTo(collectionManager.getMin()) < 0){
            this.collectionManager.add(dragon);
            this.collectionManager.sortCollection();
            return new Response(ResponseCode.OK);
        }
        else {
            Response response = new Response(ResponseCode.OK_WITH_MESSAGE);
            response.setMessage("Element not added. It is greater than min of collection");
            return response;
        }
    }

    @Override
    public String getDescription() {
        return "add new element to collection if their value less than minimum of collection";
    }

    @Override
    public String getName() {
        return "add_if_min";
    }
}
