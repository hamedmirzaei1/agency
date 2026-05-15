package app.features.rentedHouses;

import data.ContractManager;

import java.util.Scanner;

public class ContractDetail {
    private boolean isShowing;

    public void menu(ContractManager contractData) {
        Scanner sc = new Scanner(System.in);
        isShowing = true;

        while(isShowing) {
            System.out.println("Enter a contract id to get details\nor");
            System.out.println("0. Back");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String id = sc.nextLine();

            if (id.equals("0")) {
                isShowing = false;
                break;
            }
            System.out.println();
            if(checkForContract(id, contractData)) {
                System.out.println();
                System.out.println(contractData.getContracts().get(id).getDescription());
                System.out.println();
                System.out.println("house details:");
                System.out.println(contractData.getContracts().get(id).getHouse().getDescription());
                break;
            } else {
                System.out.println("id is not valid");
                System.out.println();
            }

        }
    }

    public static boolean checkForContract(String id, ContractManager contractData) {
        if(contractData.getContracts().containsKey(id) && contractData.getContracts().get(id).getValid()) {
            return true;
        } else {
            return false;
        }
    }
}
