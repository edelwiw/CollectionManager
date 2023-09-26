package ClientCommands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserData;

public class Help implements ClientCommand{

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
        if (response.getResponseCode().equals(ResponseCode.OK)){
            System.out.println(response.getPayload());
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }
}
