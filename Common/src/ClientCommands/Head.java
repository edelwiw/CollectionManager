package ClientCommands;

import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserData;

public class Head implements ClientCommand{

    private UserData userData;

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {

    }

    @Override
    public void handleResponse(Response response) {
        if (response.getResponseCode().equals(ResponseCode.OK)) {
            Dragon dragon = (Dragon) response.getPayload();
            System.out.println(dragon);
        }
        else if (response.getResponseCode().equals(ResponseCode.OK_WITH_MESSAGE)){
            System.out.println(response.getMessage());
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }
}

