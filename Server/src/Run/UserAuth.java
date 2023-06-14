package Run;

import Utils.UserData;

public class UserAuth {

    private final DatabaseConnector database;

    public UserAuth(DatabaseConnector connector) {
        this.database = connector;
    }

    public boolean isUserExist(UserData userData){
        // TODO
        return false;
    }
}
