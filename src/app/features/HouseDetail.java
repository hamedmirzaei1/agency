package app.features;

import data.HouseManager;

import java.util.Scanner;

public class HouseDetail {
    private boolean isShowing;

    public void menu(HouseManager houseData) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while (isShowing) {

            System.out.print("Enter a house id to get details: ");
            String id = sc.nextLine();

            if(checkForHouse(id, houseData)) {
                System.out.println();
                System.out.println(detail(id, houseData));
                break;
            } else {
                System.out.println("id is not valid");
            }

        }
    }

    public static String detail(String id, HouseManager houseData) {
        id = id.trim();
        String description = houseData.getHouses().get(id).getDescription();
        description += "\n\n" + "owner: " + houseData.getHouses().get(id).getOwnerName();
        description += "\n" + "status: " + houseData.getHouses().get(id).getStatus();
        return description;
    }

    public static boolean checkForHouse(String id, HouseManager houseData) {
        id = id.trim();
        if(houseData.getHouses().containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public static String shortDescription(String id, HouseManager houseData) {
        id = id.trim();
        return houseData.getHouses().get(id).getDescription();
    }

    public void ME() {
    }
}
