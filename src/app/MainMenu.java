package app;


import app.features.*;
import app.features.rentedHouses.CancelContract;
import app.features.rentedHouses.ContractDetail;
import app.features.rentedHouses.ContractList;
import app.features.rentedHouses.RentedHouseList;

import java.util.Scanner;

public class MainMenu {
    private HouseSubmit houseSubmit = new HouseSubmit();

    private HouseList houseList = new HouseList();
    private HouseDetail houseDetail = new HouseDetail();

    private RentedHouseList rentedHouseList = new RentedHouseList();
    private ContractList contractList = new ContractList();
    private ContractDetail contractDetail = new ContractDetail();
    private CancelContract cancelContract = new CancelContract();

    private BuyingMenu buyingMenu = new BuyingMenu();
    private RentingMenu rentingMenu = new RentingMenu();


    boolean isShowing = true;

    public void menu(Session session) {
        Scanner sc = new Scanner(System.in);
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
            System.out.println("10. Submit house for sale");
            System.out.println("11. Submit house for rent");
            System.out.println("12. Submit for *fast* sale");
            System.out.println();
            System.out.println("13. exit");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");

            String command = sc.nextLine();

            System.out.println();

            switch(command) {
                case "1":
                    System.out.println("Your budget:  $" + session.getCurrentUser().getBudget());
                    break;
                case "2":
                    houseList.currentUserHouses(session.getCurrentUser(), session.getHouseData());
                    break;
                case "3":
                    rentedHouseList.menu(session.getCurrentUser(), session.getHouseData(), session.getContractData());
                    break;
                case "4":
                    buyingMenu.menu(session.getHouseData(), session.getUserData(), session.getCurrentUser());
                    break;
                case "5":
                    rentingMenu.menu(session.getHouseData(), session.getContractData(), session.getCurrentUser());
                    break;
                case "6":
                    houseDetail.menu(session.getHouseData());
                    break;
                case "7":
                    contractList.menu(session.getContractData(), session.getCurrentUser());
                    break;
                case "8":
                    contractDetail.menu(session.getContractData());
                    break;
                case "9":
                    cancelContract.menu(session);
                    break;
                case "10":
                    houseSubmit.menu("forSale", sc, session.getHouseData(), session.getCurrentUser());
                    break;
                case "11":
                    houseSubmit.menu("forRent", sc, session.getHouseData(), session.getCurrentUser());
                    break;
                case "13":
                    session.endSession();
                    break;
                default:
                    System.out.println("NOT VALID OPTION");
            }
            System.out.println();

        }
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }
}
