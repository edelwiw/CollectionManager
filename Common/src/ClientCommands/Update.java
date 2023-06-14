package ClientCommands;


import Collection.Dragon;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.CLIManager;
import Utils.Response;
import Utils.ResponseCode;
import Utils.UserData;

public class Update implements ClientCommand {

    private UserData userData;

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
    }

    private int id;
    private Dragon dragon;

    public Dragon getDragon() {
        return dragon;
    }

    public int getId() {
        return id;
    }

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"id\" argument");
        int id;
        try{
            id = Integer.parseInt(args[1]);
            this.id = id;
        }
        catch (NumberFormatException e){
            throw  new WrongArgument("Argument must be long integer number.");
        }

        CLIManager cliManager = new CLIManager();
        Dragon dragon = new Dragon();
        cliManager.requestDragon(dragon);
        this.dragon = dragon;
    }

    @Override
    public void handleResponse(Response response) {
        if(response.getResponseCode().equals(ResponseCode.OK)){
            System.out.println("Element added successfully");
        } else if (response.getResponseCode().equals(ResponseCode.OK_WITH_MESSAGE)) {
            System.out.println(response.getMessage());
        } else {
            System.out.println("Request failed with message: " + response.getMessage());
        }
    }
}
