package RemoteCommands;

import ClientCommands.ClientCommand;
import ClientCommands.FilterLessThanAge;
import Collection.Dragon;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

import java.util.List;
import java.util.stream.Stream;

public class FilterLessThanAgeServer implements ServerCommand {

    private final CollectionManager collectionManager;

    public FilterLessThanAgeServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        if(collectionManager.getSize() == 0){
            Response response = new Response(ResponseCode.OK_WITH_MESSAGE);
            response.setMessage("Nothing to show. Collection empty");
            return response;
        }

        FilterLessThanAge filterLessThanAge = (FilterLessThanAge) command;
        long age = filterLessThanAge.getAge();

        Stream<Dragon> stream = collectionManager.getStream();

        List<Dragon> filtered = stream.filter((x) -> x.getAge() < age).toList();

        Response response = new Response(ResponseCode.OK);
        response.setPayload(filtered);
        return response;
    }

    @Override
    public String getDescription() {
        return "shows all elements with age greater than spec. value";
    }

    @Override
    public String getName() {
        return "filter_less_than_age";
    }
}
