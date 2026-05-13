package app.features;

import data.UserManager;

import java.util.Scanner;

public class UserDetail {
    private boolean isShowing;

    public void menu(UserManager userData) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while (isShowing) {

            System.out.println("Enter a user id to get details\nor");
            System.out.println("0. Back");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String id = sc.nextLine();
            id = id.trim();

            if (id.equals("0")) {
                isShowing = false;
                break;
            }
            System.out.println();
            if(checkForUser(id, userData)) {
                System.out.println(userData.getIdToUser().get(id).getDescription());
            } else {
                System.out.println("id is not valid");
            }
            System.out.println();
        }
    }

    private boolean checkForUser(String id, UserManager userData) {
        if(userData.getIdToUser().containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }
}
