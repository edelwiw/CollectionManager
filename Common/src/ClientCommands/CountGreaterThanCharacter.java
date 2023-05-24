package ClientCommands;

import Collection.DragonCharacter;
import Exceptions.NotEnoughArgs;
import Exceptions.WrongArgument;
import Utils.Response;
import Utils.ResponseCode;

import java.util.Arrays;

public class CountGreaterThanCharacter implements ClientCommand{

    private DragonCharacter dragonCharacter;

    public DragonCharacter getDragonCharacter() {
        return dragonCharacter;
    }

    @Override
    public void prepareRequest(String[] args) throws WrongArgument, NotEnoughArgs {
        if(args.length < 2) throw new NotEnoughArgs("Command requires \"Character\" argument");
        DragonCharacter character;
        try{
            character = DragonCharacter.valueOf(args[1].toUpperCase());
            this.dragonCharacter = character;
        }
        catch (IllegalArgumentException e){
            throw  new WrongArgument("Character must be selected from " + Arrays.toString(DragonCharacter.values()) + ".");
        }
    }

    @Override
    public void handleResponse(Response response) {
        if (response.getResponseCode().equals(ResponseCode.OK)) {
            System.out.println("Number of elements is " + response.getPayload());
        } else {
            System.out.println("Request failed with message " + response.getMessage());
        }

    }
}
