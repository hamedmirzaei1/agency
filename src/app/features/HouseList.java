package app.features;

import data.HouseManager;
import user.User;

import java.util.Scanner;

public class HouseList {
    private boolean isShowing = true;

    public void currentUserHouses(User currentUser, HouseManager houseData) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while (isShowing) {
            int counter = 0;

            System.out.println("------ My houses ------");
            System.out.println();
            for (String id : houseData.getHouses().keySet()) {
                if (houseData.getHouses().get(id).getOwner().getID().equals(currentUser.getID())) {
                    counter++;
                    System.out.println(counter + "- " + id + "   status: " + houseData.getHouses().get(id).getStatus());
                }
            }

            System.out.println();
            System.out.println("0. Back");
            System.out.println("or ");
            System.out.println("Enter a house id for more details");

            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();
            System.out.println();

            if(command.equals("0")) {
                isShowing = false;
                break;
            }
            else if (HouseDetail.checkForHouse(command, houseData)){
                System.out.println(HouseDetail.shortDescription(command, houseData));
                System.out.println();
                System.out.println("==================================");
            } else {
                System.out.println("id is not valid");
                System.out.println();
                System.out.println("==================================");
            }

        }
    }
}
