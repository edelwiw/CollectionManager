package RemoteCommands;


import ClientCommands.ClientCommand;
import ClientCommands.CountGreaterThanCharacter;
import Collection.Dragon;
import Collection.DragonCharacter;
import Run.CollectionManager;
import Utils.Response;
import Utils.ResponseCode;

import java.util.stream.Stream;

public class CountGreaterThanCharacterServer implements ServerCommand{

    private final CollectionManager collectionManager;

    public CountGreaterThanCharacterServer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(ClientCommand command) {
        CountGreaterThanCharacter countGreaterThanCharacter = (CountGreaterThanCharacter) command;
        DragonCharacter character = countGreaterThanCharacter.getDragonCharacter();

        Stream<Dragon> stream = collectionManager.getStream();

        Response response = new Response(ResponseCode.OK);
        response.setPayload(stream.filter((x) -> x.getCharacter().compareTo(character) > 0).count());

//        System.out.println("Number of elements greater than " + character + " is " + stream.filter((x) -> x.getCharacter().compareTo(character) > 0).count());
        return response;
    }

    @Override
    public String getDescription() {
        return "print number of elements with character greater than spec.";
    }

    @Override
    public String getName() {
        return "count_greater_than";
    }
}
