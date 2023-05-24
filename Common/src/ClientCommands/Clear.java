package ClientCommands;


import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;

public class Clear implements ClientCommand{
    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {

    }

    @Override
    public void handleResponse(Response response) {
        if (response.getResponseCode().equals(ResponseCode.OK)){
            System.out.println("Collection cleared successfully");
        } else {
            System.out.println(response.getMessage());
        }
    }
}
