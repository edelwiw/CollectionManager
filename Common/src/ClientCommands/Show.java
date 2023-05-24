package ClientCommands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;

public class Show implements ClientCommand{

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {

    }

    @Override
    public void handleResponse(Response response) {
        if (response.getResponseCode().equals(ResponseCode.OK)) {
            Dragon[] array = (Dragon[]) response.getPayload();
            if (array.length == 0) {
                System.out.println("Collection empty");
                return;
            }
            for (Dragon dragon : array) {
                System.out.println("-----------------------");
                System.out.println(dragon.getName());
                System.out.println(dragon);
            }
            System.out.println("-----------------------");
        }
        else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }

}
