package ClientCommands;


import Collection.Coordinates;
import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserData;

import java.util.List;
import java.util.Map;

public class GroupCountingByCoordinates implements ClientCommand{

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
        if(response.getResponseCode().equals(ResponseCode.OK)) {
            Map<Coordinates, List<Dragon>> grouped = (Map<Coordinates, List<Dragon>>) response.getPayload();

            System.out.println("Items count:");
            for (Coordinates entry : grouped.keySet()) {
                System.out.println(entry + " - " + grouped.get(entry).size());
            }
        } else if (response.getResponseCode().equals(ResponseCode.OK_WITH_MESSAGE)) {
            System.out.println(response.getMessage());
        } else {
            System.out.println("Request failed with message: " + response.getMessage());
        }
    }
}
