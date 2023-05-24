package ClientCommands;

import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;

public class RemoveByID implements ClientCommand{

    private long id;

    public long getId() {
        return id;
    }

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"id\" argument");
        long id;
        try{
            id = Long.parseLong(args[1]);
            this.id = id;
        }
        catch (NumberFormatException e){
            throw  new WrongArgument("Argument must be long integer number");
        }

    }

    @Override
    public void handleResponse(Response response) {
        if (response.getResponseCode().equals(ResponseCode.OK)){
            System.out.println("Element removed successfully");
        } else {
            System.out.println(response.getMessage());
        }
    }
}
