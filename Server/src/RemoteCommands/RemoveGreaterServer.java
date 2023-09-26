package RemoteCommands;


import ClientCommands.ClientCommand;
import ClientCommands.RemoveGreater;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

import java.util.Iterator;

public class RemoveGreaterServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public RemoveGreaterServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        Iterator<Dragon> iter = collectionManager.getIterator();

        if(!iter.hasNext()) {
            Response response = new Response(ResponseCode.OK_WITH_MESSAGE);
            response.setMessage("Nothing to remove. Collection empty");
            return response;
        }

        RemoveGreater removeGreater = (RemoveGreater) command;
        Dragon dragon = removeGreater.getDragon();
        int counter = 0;

        while (iter.hasNext()){
            Dragon el = iter.next();
            if(el.compareTo(dragon) > 0) {
                if (el.getCreatedBy() == command.getUser().getId()) {
                    collectionManager.removeById(el.getId());
                    counter += 1;
                }
            }
        }
        Response response = new Response(ResponseCode.OK);
        response.setPayload(counter);
        return response;
    }

    @Override
    public String getDescription() {
        return "remove all elements greater that this";
    }

    @Override
    public String getName() {
        return "remove_greater";
    }
}
