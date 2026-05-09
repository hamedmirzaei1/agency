package app.signup;

import data.UserManager;
import user.User;

import java.util.Scanner;

public class Session {
    private User currentUser;


    public User getCurrentUser() {
        return currentUser;
    }

    public void loginMenu(Scanner sc, UserManager data) {
        boolean isShowing = true;

        while (isShowing) {
            data.loadUser();

            System.out.println("==================================");
            System.out.println("1.  Login");
            System.out.println("2.  SignUp");
            System.out.println();
            System.out.println("3.  exit");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");

            String command = sc.nextLine();

            System.out.println();

            switch(command) {
                case "1":
                    if(login(sc, data)) {
                        isShowing = false;
                    }
                    break;
                case "2":
                    if(signUp(sc, data)) {
                        isShowing = false;
                    }
                    break;
                case "3":
                    isShowing = false; // todo: make the exit standard (with save)
                default:
                    System.out.println("NOT VALID OPTION");
            }
            System.out.println();
        }
    }

    private boolean login(Scanner sc, UserManager data) {
        System.out.print("Enter your username: ");
        String userName = sc.nextLine();
        if(data.getUserNameToID().containsKey(userName)) {
            User checkUser = data.getIdToUser().get(data.getUserNameToID().get(userName));

            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            if(SignUpTools.hash(password).equals(checkUser.getHashedPassword())) {
                System.out.println();
                System.out.println("Login Successful");
                currentUser = checkUser;
                return true;
            } else {
                System.out.println("Wrong Password");
                return false;
            }

        } else {
            System.out.println("There is no such user");
            System.out.println("Create an account using -SignUp- or -Login- with a valid username");
            return false;
        }

    }

    private boolean signUp(Scanner sc, UserManager data) {

        System.out.println("Enter a username (It must contain only -small letters- and -numbers-)");
        System.out.print(">> ");
        String inputUserName = sc.nextLine();
        if(!SignUpTools.checkUserName(inputUserName, data)) {
            return false;
        }

        System.out.println("Enter a password");
        System.out.print(">> ");
        String inputPassword = sc.nextLine();
        if(!(SignUpTools.checkPassword(sc, inputPassword))) {
            return false;
        }
        System.out.println("Enter your name");
        System.out.print(">> ");
        String name = sc.nextLine();

        currentUser = new User(inputUserName, SignUpTools.hash(inputPassword), name);
        data.getIdToUser().put(currentUser.getID(), currentUser);
        data.updateUsersFile();

        System.out.println();
        System.out.println("SignUp Successful");
        return true;
    }


    public static void endSession() {
        //todo
    }
}
