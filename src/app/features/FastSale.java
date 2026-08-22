package app.features;

import app.Session;
import contract.TradeTools;
import data.HouseManager;
import house.Apartment;
import house.House;
import house.PentHouse;
import house.VillaHouse;
import user.User;

import java.util.Scanner;

import static contract.TradeTools.fastTrade;

public class FastSale {
    private boolean isShowing;

    public void menu(HouseManager houseData, User currentUser, Session session) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while (isShowing) {
            try {
                House inputHouse;
                System.out.println();
                System.out.println("Enter a house id of yours\nor");
                System.out.println("for adding a new house, choose the house type:");
                System.out.println("1. Apartment");
                System.out.println("2. PentHouse");
                System.out.println("3. VillaHouse");
                System.out.println();
                System.out.println("4. Back");
                System.out.println("==================================");
                System.out.print("CHOOSE AN OPTION: ");
                String command = sc.nextLine();
                System.out.println();

                if (HouseDetail.checkForHouse(command, houseData)) {
                    House house = houseData.getHouses().get(command);
                    if (house.getOwner().getID().equals(currentUser.getID()) && !house.getStatus().equals("rented")) {
                        sale(house, session, false);
                        break;
                    } else {
                        System.out.println("you can't sale this house");
                        continue;
                    }
                }

                switch (command) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        isShowing = false;
                        continue;
                    default:
                        System.out.println("NOT VALID");
                        System.out.println();
                        continue;
                }
                System.out.print("Enter house area: ");
                int area = Integer.parseInt(sc.nextLine());
                System.out.print("Enter floor: ");
                int floor = Integer.parseInt(sc.nextLine());
                System.out.print("Enter number of floors: ");
                int numberOfFloors = Integer.parseInt(sc.nextLine());
                System.out.print("Enter number of bedrooms: ");
                int roomNumbers = Integer.parseInt(sc.nextLine());
                System.out.print("Enter number of bathrooms: ");
                int bathroomNumbers = Integer.parseInt(sc.nextLine());
                int region;
                while (true) {
                    System.out.print("Enter house region (1, 2, 3 or 4): ");
                    region = Integer.parseInt(sc.nextLine());
                    if (region > 4 || region < 1) {
                        System.out.println("Region is not valid");
                    } else {
                        break;
                    }
                }

                switch (command) {
                    case "1":
                        System.out.print("Enter number of units: ");
                        int numberOfUnits = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter unit number of the house: ");
                        int unitNumber = Integer.parseInt(sc.nextLine());

                        inputHouse = new Apartment(houseData, currentUser, "forSaleForRent", area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, numberOfUnits, unitNumber);
                        sale(inputHouse, session, true);
                        break;
                    case "2":
                        System.out.print("Enter terrace area: ");
                        int terraceArea = Integer.parseInt(sc.nextLine());

                        if(floor != numberOfFloors) {
                            System.out.println("not valid\nthe penthouse must be at the highest floor");
                            break;
                        }

                        inputHouse = new PentHouse(houseData, currentUser, "forSaleForRent", area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, terraceArea);
                        sale(inputHouse, session, true);
                        break;
                    case "3":
                        System.out.print("Enter yard area: ");
                        int yardArea = Integer.parseInt(sc.nextLine());

                        inputHouse = new VillaHouse(houseData, currentUser, "forSaleForRent", area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, yardArea);
                        sale(inputHouse, session, true);
                        break;
                }

                System.out.println();

            } catch (Exception e) {
                System.out.println("Input is not valid");
                System.out.println();
            }
        }
    }
    private void sale(House inputHouse, Session session, boolean newSub) {
        Scanner sc = new Scanner(System.in);

        System.out.println();


        System.out.println("price: ");
        System.out.println("$" + (int)(inputHouse.getPrice() * 0.9));
        System.out.println("[10% less than normal sale]");

        System.out.println();

        while (true) {
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();
            if (command.equals("1")) {
                TradeTools.fastTrade(session.getSuperUser(), session.getCurrentUser(), inputHouse, inputHouse.getPrice());
                session.getHouseData().updateHousesFile();
                session.getUserData().updateUsersFile();
                System.out.println();
                System.out.println("House was successfully sold");
                isShowing = false;
                break;
            }
            if (command.equals("2")) {
                if (newSub) {
                    session.getHouseData().getHouses().remove(inputHouse.getId());
                }
                isShowing = true;
                break;
            }
            System.out.println();
        }
    }

}
