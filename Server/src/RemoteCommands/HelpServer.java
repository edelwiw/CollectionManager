package RemoteCommands;


import ClientCommands.ClientCommand;
import Utils.Response;
import Utils.ResponseCode;

import java.lang.reflect.Type;
import java.util.HashMap;

public class HelpServer implements ServerCommand{

    private final HashMap<Type, ServerCommand> commands;

    public HelpServer(HashMap<Type, ServerCommand> commands) {
        this.commands = commands;
    }

    @Override
    public Response execute(ClientCommand command) {
        StringBuilder res = new StringBuilder();
        for(Type el : this.commands.keySet()){
            res.append(String.format("%-35s - %s\n", this.commands.get(el).getName(), this.commands.get(el).getDescription()));
        }
        res.append(String.format("%-35s - %s\n", "close", "close connection"));
        Response response = new Response(ResponseCode.OK);
        response.setPayload(res.toString());
        return response;
    }

    @Override
    public String getDescription() {
        return "shows all available commands (this command)";
    }

    @Override
    public String getName() {
        return "help";
    }
}
