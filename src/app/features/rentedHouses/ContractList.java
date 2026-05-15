package app.features.rentedHouses;

import contract.RentContract;
import data.ContractManager;
import user.User;

import java.util.Scanner;

public class ContractList {
    private boolean isShowing;

    public void menu(ContractManager contractData, User currentUser) {
        Scanner sc = new Scanner(System.in);


        isShowing = true;
        while(isShowing) {
            int counter = 0;

            System.out.println("------ My Contracts ------");
            System.out.println();
            System.out.println("[The id's are related to contracts]");
            for(String id : contractData.getContracts().keySet()) {
                RentContract contract = contractData.getContracts().get(id);
                if (contract.getSecondOne().getID().equals(currentUser.getID()) && contract.getValid()) {
                    counter++;
                    System.out.println(counter + "- " + id + "   rented by you");
                }
                if(contract.getLandlord().getID().equals(currentUser.getID()) && contract.getValid()) {
                    counter++;
                    System.out.println(counter + "- " + id + "   rented from you");
                }
            }
            if (counter == 0) {
                System.out.println("You have no contracts");
            }
            System.out.println();

            System.out.println("0. Back");
            System.out.println("or");
            System.out.println("Enter a contract id for more details");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();
            command = command.trim();

            if(command.equals("0")) {
                isShowing = false;
                break;
            } else {
                System.out.println();
                if(ContractDetail.checkForContract(command, contractData)) {
                    System.out.println(contractData.getContracts().get(command).getDescription());
                    System.out.println();
                    System.out.println("house details:");
                    System.out.println(contractData.getContracts().get(command).getHouse().getDescription());
                    System.out.println();
                } else {
                    System.out.println("id is not valid");
                }
                System.out.println();
            }
        }
    }
}
