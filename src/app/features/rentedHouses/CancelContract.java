package app.features.rentedHouses;

import app.Session;
import contract.TradeTools;

import java.util.Scanner;

public class CancelContract {
    private boolean isShowing;

    public void menu(Session session) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while (isShowing) {
            System.out.println("Enter a contract id to cancel");
            System.out.println("or");
            System.out.println("0. Back");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");

            String command = sc.nextLine();
            command = command.trim();

            if(command.equals("0")) {
                isShowing = false;
                break;
            }

            System.out.println();
            if (ContractDetail.checkForContract(command, session.getContractData())) {
                if (session.getCurrentUser().getID().equals(session.getContractData().getContracts().get(command).getSecondOne().getID())) {
                    while (true) {
                        System.out.println("Cancelling penalty:  $" + TradeTools.getCancelPenalty(session.getContractData().getContracts().get(command).getHouse()));
                        System.out.println("\n1. Confirm");
                        System.out.println("2. Cancel");
                        System.out.println("==================================");
                        System.out.print("CHOOSE AN OPTION: ");
                        String option = sc.nextLine();

                        if(option.equals("1")) {
                            if (TradeTools.cancelRent(session.getContractData().getContracts().get(command), session.getUserData())) {
                                System.out.println("contract canceled successfully");
                                Session.updateFiles(session);
                                isShowing = false;
                                break;
                            } else {
                                System.out.println();
                                System.out.println("not enough budget for canceling penalty");
                                break;
                            }
                        }
                        if(option.equals("2")) {
                            isShowing = false;
                            break;
                        }
                        System.out.println();
                    }
                }
                else{
                    System.out.println("you are not the one who rented the house");
                }
                System.out.println();
            } else {
                System.out.println("id is not valid");
                System.out.println();
            }
        }
    }
}
