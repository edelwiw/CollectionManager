package ClientCommands;


import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;

public class Info implements ClientCommand{

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {

    }

    @Override
    public void handleResponse(Response response) {
        if (response.getResponseCode().equals(ResponseCode.OK)){
            String info = (String) response.getPayload();
            System.out.println(info);
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }
}
