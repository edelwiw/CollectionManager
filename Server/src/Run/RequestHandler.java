package Run;

import ClientCommands.*;
import RemoteCommands.*;
import Utils.Response;
import Utils.ResponseCode;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Class for parsing request, executing command and getting response
 */
public class RequestHandler {

    private final CollectionManager collectionManager;
    private final HashMap<Type, ServerCommand> commandMapper;

    public RequestHandler(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandMapper = new HashMap<Type, ServerCommand>();

//        commandMapper.put(Help.class, new HelpServer(this.commandMapper));
        commandMapper.put(Show.class, new ShowServer(this.collectionManager));
        commandMapper.put(Add.class, new AddServer(this.collectionManager));
        commandMapper.put(Info.class, new InfoServer(this.collectionManager));
//        commandMapper.put(RemoveByID.class, new RemoveByIDSever(this.collectionManager));
//        commandMapper.put(AddIfMin.class, new AddIfMinServer(this.collectionManager));
//        commandMapper.put(Clear.class, new ClearServer(this.collectionManager));
//        commandMapper.put(Head.class, new HeadServer(this.collectionManager));
//        commandMapper.put(RemoveGreater.class, new RemoveGreaterServer(this.collectionManager));
//        commandMapper.put(FilterLessThanAge.class, new FilterLessThanAgeServer(this.collectionManager));
//        commandMapper.put(Update.class, new UpdateServer(this.collectionManager));
//        commandMapper.put(CountGreaterThanCharacter.class, new CountGreaterThanCharacterServer(this.collectionManager));
        commandMapper.put(GroupCountingByCoordinates.class, new GroupCountingByCoordinatesServer(this.collectionManager));
    }

    public Response executeCommand(ClientCommand clientCommand){

        ServerCommand command = this.commandMapper.get(clientCommand.getClass());
        if (command == null){
            Response response = new Response(ResponseCode.ERROR);
            response.setMessage("Command not found on server!");
            return response;
        }
        try {
            Response response = command.execute(clientCommand);
            return response;
        } catch (Throwable e){
            e.printStackTrace();
            Response response = new Response(ResponseCode.ERROR);
            response.setMessage("Error while command execution");
            return response;
        }

    }
}
