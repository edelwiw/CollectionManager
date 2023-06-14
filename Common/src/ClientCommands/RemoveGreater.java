package ClientCommands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.CLIManager;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserData;

public class RemoveGreater implements ClientCommand{

    private UserData userData;

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
    }

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
            int count = (int) response.getPayload();
            System.out.println(count + " elements was removed");
        } else if (response.getResponseCode().equals(ResponseCode.OK_WITH_MESSAGE)) {
            System.out.println(response.getMessage());
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }
}
