package ClientCommands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.CLIManager;
import Utils.Response;
import Utils.ResponseCode;


public class Add implements ClientCommand{

    private Dragon dragon;

    public Dragon getDragon() {
        return dragon;
    }


    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {
        CLIManager cliManager = new CLIManager();
        Dragon dragon = new Dragon();
        cliManager.requestDragon(dragon);
        this.dragon = dragon;
    }

    @Override
    public void handleResponse(Response response) {
        if(response.getResponseCode().equals(ResponseCode.OK)){
            System.out.println("Element added successfully");
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }
}
