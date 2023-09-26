package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.stream.Stream;

public class User {
    private int id;
    private String username;
    private String name;
    private String surname;
    private String passHash;
    private String salt;


    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean validatePass(String pass){
        String hash = this.getHash(pass + this.getSalt());
        return Objects.equals(hash, this.getPassHash());
    }

    public String getHash(String line){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ignored) {}

        byte[] md = messageDigest.digest(line.getBytes());

        BigInteger no = new BigInteger(1, md);

        // Convert message digest into hex value
        String hashtext = no.toString(16);

        return hashtext;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
