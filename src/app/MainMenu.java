package app;

import user.User;

import java.util.Scanner;

public class MainMenu {
    boolean isShowing = true;

    public void startMenu(Scanner sc, User currentUser) {
        while(isShowing) {
            System.out.println("==================================");
            System.out.println("1.  My budget");
            System.out.println("2.  My houses");
            System.out.println("3.  My rented houses");
            System.out.println();
            System.out.println("4.  Houses for sale");
            System.out.println("5.  Houses for rent");
            System.out.println();
            System.out.println("6.  House details using ID");
            System.out.println();
            System.out.println("7.  My contracts");
            System.out.println("8.  Contract details using ID");
            System.out.println("9.  Cancel a contract");
            System.out.println();
            System.out.println("9.  Submit house for sale");
            System.out.println("10. Submit house for rent");
            System.out.println("11. Submit for *fast* sale");
            System.out.println();
            System.out.println("12. exit");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");

            String command = sc.nextLine();

            System.out.println();

            switch(command) {
                case "1":
                    System.out.println("Your budget: " + currentUser.getBudget());
                    break;
                case "12":
                    isShowing = false;
                    break;
                default:
                    System.out.println("NOT VALID OPTION");
            }
            System.out.println();

        }
    }
}
