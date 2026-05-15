package app.features;

import contract.TradeTools;
import data.ContractManager;
import data.HouseManager;
import data.UserManager;
import user.User;

import java.util.Scanner;

public class BuyingMenu {
    private boolean isShowing;

    public void menu(HouseManager houseData, UserManager userData, ContractManager contractData, User currentUser) {
        Scanner sc = new Scanner(System.in);
        isShowing = true;
        while(isShowing) {
            int counter = 0;

            System.out.println();
            System.out.println("------ Houses for sale ------");
            System.out.println();
            for(String id : houseData.getHouses().keySet()) {
                if((houseData.getHouses().get(id).getStatus().equals("forSale") ||
                        houseData.getHouses().get(id).getStatus().equals("forSaleForRent"))&&
                        !houseData.getHouses().get(id).getOwner().getID().equals(currentUser.getID())) {
                    counter++;

                    System.out.print(counter + "- " + id);
                    System.out.println("   $" + houseData.getHouses().get(id).getPrice());
                }
            }
            if(counter == 0) {
                System.out.println("There are no houses submitted for sale");
                isShowing = false;
                break;
            }

            System.out.println();
            System.out.println("0. Back");
            System.out.println("or");
            System.out.println("Enter a house id for more details and purchasing: ");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();
            command = command.trim();

            if(command.equals("0")) {
                isShowing = false;
                break;
            }
            if(HouseDetail.checkForHouse(command, houseData)) {
                System.out.println();
                System.out.println(HouseDetail.detail(command, houseData, contractData));
                System.out.println();
                while (true) {
                    System.out.println("$" + houseData.getHouses().get(command).getPrice());
                    System.out.println();
                    System.out.println("1. Purchase");
                    System.out.println("2. Back");
                    System.out.println("==================================");
                    System.out.print("CHOOSE AN OPTION: ");
                    String option = sc.nextLine();
                    if(option.equals("1")) {
                        if (houseData.getHouses().get(command).getStatus().equals("forSale") || houseData.getHouses().get(command).getStatus().equals("forSaleForRent")) {
                                if(buy(command, currentUser, houseData, userData)) {
                                    isShowing = false;
                                    break;
                                }
                        } else {
                            System.out.println();
                            System.out.println("you can't purchase the house");
                            break;
                        }
                    } if (option.equals("2")) {
                        break;
                    }
                }
            } else {
                System.out.println();
                System.out.println("id is not valid");
            }
        }
    }

    private boolean buy(String id, User currentUser, HouseManager houseData, UserManager userData) {
        if(TradeTools.trade(houseData.getHouses().get(id).getOwner(), currentUser, houseData.getHouses().get(id) ,houseData.getHouses().get(id).getPrice(), userData)) {
            System.out.println();
            System.out.println("The house was successfully purchased");
            houseData.updateHousesFile();
            userData.updateUsersFile();
            return true;
        }
        else {
            return false;
        }
    }
}
