package app.features;

import contract.RentContract;
import data.ContractManager;
import data.HouseManager;
import house.PentHouse;
import user.User;

import java.util.Scanner;

public class RentingMenu {
    private boolean isShowing;

    public void menu(HouseManager houseData, ContractManager contractData, User currentUser) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while(isShowing) {
            System.out.println();
            System.out.println("------ Houses for rent ------");
            System.out.println();

            int counter = 0;

            for(String id : houseData.getHouses().keySet()) {
                if(houseData.getHouses().get(id).getStatus().equals("forRent") && !houseData.getHouses().get(id).getOwner().getID().equals(currentUser.getID())) {
                    counter++;
                    System.out.println(counter + "- " + houseData.getHouses().get(id).getId() + "   $" + houseData.getHouses().get(id).getMonthlyRentPrice() + " per month");
                }
            }
            if(counter == 0) {
                System.out.println("There are no houses submitted for rent");
                isShowing = false;
                break;
            }

            System.out.println();
            System.out.println("0. Back");
            System.out.println("or");
            System.out.println("Enter a house id for more details and renting: ");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();
            command = command.trim();

            if(command.equals("0")) {
                isShowing = false;
                break;
            }
            System.out.println();
            if(HouseDetail.checkForHouse(command, houseData)) {
                if(houseData.getHouses().get(command).getStatus().equals("forRent") && !houseData.getHouses().get(command).getOwner().getID().equals(currentUser.getID())) {
                    if(rent(command, houseData, contractData, currentUser)) {
                        System.out.println();
                        System.out.println("The house was successfully rented");
                        isShowing = false;
                    }
                }
            } else {
                System.out.println("id is not valid");
            }
            System.out.println();
        }
    }

    private boolean rent(String id, HouseManager houseData, ContractManager contractData, User currentUser) {
        Scanner sc = new Scanner(System.in);
        System.out.println(HouseDetail.detail(id, houseData));
        System.out.println();

        System.out.println("$" + houseData.getHouses().get(id).getMonthlyRentPrice() + " per month");
        System.out.println();

        while(true) {
            System.out.println("1. Rent");
            System.out.println("2. Back");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();

            if(command.equals("2")) {
                return false;
            }
            if(command.equals("1")) {
                RentContract rentContract = new RentContract(houseData.getHouses().get(id).getOwner(), currentUser, houseData.getHouses().get(id), houseData.getHouses().get(id).getMonthlyRentPrice());
                contractData.getContracts().put(rentContract.getId(), rentContract);
                contractData.updateContractsFile();
                houseData.updateHousesFile();
                return true;
            }
        }
    }
}
