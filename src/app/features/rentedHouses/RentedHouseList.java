package app.features.rentedHouses;

import app.features.HouseDetail;
import data.ContractManager;
import data.HouseManager;
import user.User;

import java.util.Scanner;

public class RentedHouseList {
    private boolean isShowing;

    public void menu(User currentUser, HouseManager houseData, ContractManager contractData) {
        Scanner sc = new Scanner(System.in);
        isShowing = true;
        while(isShowing) {
            int counter = 0;
            System.out.println("------ My rented houses ------");
            System.out.println();
            for(String id : contractData.getContracts().keySet()) {
                if (contractData.getContracts().get(id).getSecondOne().getID().equals(currentUser.getID()) && contractData.getContracts().get(id).getValid()) {
                    counter++;
                    System.out.println(counter + "- " + contractData.getContracts().get(id).getHouse().getId());
                }
            }
            if (counter == 0) {
                System.out.println("You have no rented houses");
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
