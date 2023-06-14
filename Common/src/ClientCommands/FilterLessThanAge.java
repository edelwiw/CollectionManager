package ClientCommands;


import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserData;

import java.util.List;

public class FilterLessThanAge implements ClientCommand {

    private long age;

    private UserData userData;

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
    }

    public long getAge() {
        return age;
    }

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"age\" argument");
        long age;
        try{
            age = Long.parseLong(args[1]);
            this.age = age;
        }
        catch (NumberFormatException e){
            throw  new WrongArgument("Argument must be long integer number.");
        }
    }

    @Override
    public void handleResponse(Response response) {
        if(response.getResponseCode().equals(ResponseCode.OK)) {
            List<Dragon> filtered = (List<Dragon>) response.getPayload();

            if (filtered.size() == 0) {
                System.out.println("Nothing to show");
                return;
            }

            for (Dragon element : filtered) {{
                    System.out.println("-----------------------");
                    System.out.println(element.getName());
                    System.out.println(element);
                }
            }
            System.out.println("-----------------------");
        } else if (response.getResponseCode().equals(ResponseCode.OK_WITH_MESSAGE)) {
            System.out.println(response.getMessage());
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }
    }
}
