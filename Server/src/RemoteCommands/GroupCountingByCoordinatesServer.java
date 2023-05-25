package RemoteCommands;


import ClientCommands.ClientCommand;
import Collection.Coordinates;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupCountingByCoordinatesServer implements ServerCommand {

    private final CollectionManager collectionManager;

    public GroupCountingByCoordinatesServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        if(collectionManager.getSize() == 0) {
            Response response = new Response(ResponseCode.OK_WITH_MESSAGE);
            response.setMessage("Collection is empty. Nothing to group.");
            return response;
        }

        Map<Coordinates, List<Dragon>> grouped = collectionManager.getStream().collect(Collectors.groupingBy(Dragon::getCoordinates)); // goup

        Response response = new Response(ResponseCode.OK);
        response.setPayload(grouped);
        return response;
    }

    @Override
    public String getDescription() {
        return "print number of elements with character greater than spec.";
    }

    @Override
    public String getName() {
        return "group_counting_by_coordinates";
    }
}
