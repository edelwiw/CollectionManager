package Utils;

import java.io.Serializable;
import java.util.Scanner;

public class UserData implements Serializable {

    private String username;
    private String pass;
    private String name;
    private String surname;
    private boolean toSignUp = false;

    public void setToSignUp(boolean toSignUp) {
        this.toSignUp = toSignUp;
    }

    public boolean isToSignUp() {
        return toSignUp;
    }

    public UserData(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public void requestData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        this.name = scanner.nextLine();

        System.out.println("Enter surname");
        this.surname = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", toSignUp=" + toSignUp +
                '}';
    }
}
