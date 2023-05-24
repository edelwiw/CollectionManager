package ClientCommands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;

public class Close implements ClientCommand{

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {
        // TODO close socket
        System.exit(0);
    }

    @Override
    public void handleResponse(Response response) {

    }
}
