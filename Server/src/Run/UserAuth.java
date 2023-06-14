package Run;

import Utils.User;
import Utils.UserData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Random;

public class UserAuth {

    private final DatabaseConnector database;

    public UserAuth(DatabaseConnector connector) {
        this.database = connector;
    }

    public boolean isUserExist(UserData userData) throws SQLException {
        User user = database.readUserByUsername(userData.getUsername());
        return user != null;
    }

    private String generateSalt(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

//        System.out.println(generatedString);
        return generatedString;
    }

    public boolean auth(UserData userData) throws SQLException {
        User user = database.readUserByUsername(userData.getUsername());
        return user.validatePass(userData.getPass());
    }

    public boolean signUp(UserData userData) throws SQLException {
        User user = new User();
        user.setName(userData.getName());
        user.setSurname(userData.getSurname());
        user.setUsername(userData.getUsername());
        user.setSalt(this.generateSalt());
        user.setPassHash(user.getHash(userData.getPass() + user.getSalt()));
        database.addUser(user);
        return true;
    }
}
