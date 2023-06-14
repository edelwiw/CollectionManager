package RemoteCommands;


import ClientCommands.Add;
import ClientCommands.ClientCommand;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

public class AddServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public AddServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        Add clientCommand = (Add) command;
        Dragon dragon = clientCommand.getDragon();
        dragon.setCreatedBy(command.getUser().getId());
        System.out.println(command.getUser().getId());
        this.collectionManager.add(dragon);
        return new Response(ResponseCode.OK);
    }


    @Override
    public String getDescription() {
        return "add item to collection. Program will request each field of collection object";
    }

    @Override
    public String getName() {
        return "add";
    }
}
