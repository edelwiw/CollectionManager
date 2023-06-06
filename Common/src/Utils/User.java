package Utils;

import java.time.ZonedDateTime;
import java.util.UUID;

public class User {
    private final int id ;
    private final String name = null;
    private final String surname = null;
    private final String username;
    private final String pass;
    private final ZonedDateTime createdAt;

    public User(String username, String pass) {
        this.id = -1;
        this.createdAt = ZonedDateTime.now();
        this.username = username;
        this.pass = pass;
    }




}
