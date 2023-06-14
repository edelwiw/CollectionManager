import Utils.Response;
import Utils.ResponseCode;
import Utils.UserAuthStatus;
import Utils.UserData;

import java.net.ConnectException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class UserWorker {

    public static UserData requestUsernameAndPass(){
        // get username and pass and auth user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String pass = scanner.nextLine();

        return new UserData(username, pass);
    }

    public static boolean AuthUser(Connector connector, UserData userData) throws ConnectException {
        while (true) {
            Response response = connector.sendAndGetAuthResponse(userData);
            if (response.getResponseCode().equals(ResponseCode.OK)) {
                UserAuthStatus userAuthStatus = (UserAuthStatus) response.getPayload();

                if (userAuthStatus.equals(UserAuthStatus.SUCCESS)) {
                    System.out.println("Auth success");
                    return true;
                } else if (userAuthStatus.equals(UserAuthStatus.USER_NOT_EXIST)) {
                    System.out.println("User does not exist");
                    System.out.println("Do ypu want to create new user? [y/n]");

                    boolean answer = requestYesNo();
                    if (!answer) return false;

                    userData.requestData();
                    userData.setToSignUp(true);

                } else if (userAuthStatus.equals(UserAuthStatus.WRONG_PASS)) {
                    System.out.println("Wrong password!");
                    userData = requestUsernameAndPass();
                }
            } else {
                System.out.println(response.getMessage());
                return false;
            }
        }
    }


    private static boolean requestYesNo(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("y")) {
                return true;
            } else if (line.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Enter \"y\" or \"n\"");
            }
        }
    }
}
