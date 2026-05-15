package app.features;

import data.HouseManager;
import house.Apartment;
import house.House;
import house.PentHouse;
import house.VillaHouse;
import user.User;

import java.util.Scanner;

public class HouseSubmit {
    private boolean isShowing;

    public void menu(String status, HouseManager houseData, User currentUser) {
        Scanner sc = new Scanner(System.in);

        isShowing = true;
        while (isShowing) {
            try {
                House inputHouse;
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
                command = command.trim();

                if (loadHouse(command, houseData, currentUser, status)) {
                    if(houseData.getHouses().get(command).getStatus().equals("none")) {
                        confirmSubmit(sc, houseData.getHouses().get(command.trim()), houseData, false, status, status);
                    } else {
                        confirmSubmit(sc, houseData.getHouses().get(command.trim()), houseData, false, status, "forSaleForRent");
                    }
                    break;
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

                        inputHouse = new Apartment(houseData, currentUser, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, numberOfUnits, unitNumber);
                        confirmSubmit(sc, inputHouse, houseData, true, status, status);
                        break;
                    case "2":
                        System.out.print("Enter terrace area: ");
                        int terraceArea = Integer.parseInt(sc.nextLine());

                        if(floor != numberOfFloors) {
                            System.out.println("not valid\nthe penthouse must be at the highest floor");
                            break;
                        }
                        inputHouse = new PentHouse(houseData, currentUser, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, terraceArea);
                        confirmSubmit(sc, inputHouse, houseData, true, status, status);
                        break;
                    case "3":
                        System.out.print("Enter yard area: ");
                        int yardArea = Integer.parseInt(sc.nextLine());

                        inputHouse = new VillaHouse(houseData, currentUser, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, yardArea);
                        confirmSubmit(sc, inputHouse, houseData, true, status, status);
                        break;
                }

                System.out.println();

            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Input is not valid");
                System.out.println();
            }
        }
    }

    private void confirmSubmit(Scanner sc, House inputHouse, HouseManager data, boolean newSub, String status, String settingStatus) {
        System.out.println();

        if (status.equals("forSale")) {
            System.out.println("The house price will be: ");
            System.out.print("$" + inputHouse.getPrice());
        }
        if (status.equals("forRent")) {
            System.out.println("The House monthly rent will be: ");
            System.out.print("$" + inputHouse.getMonthlyRentPrice());
        }
        System.out.println();

        while (true) {
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            System.out.println("==================================");
            System.out.print("CHOOSE AN OPTION: ");
            String command = sc.nextLine();
            if (command.equals("1")) {
                inputHouse.setStatus(settingStatus);
                data.updateHousesFile();

                System.out.println();
                System.out.println("House was successfully submitted ");
                isShowing = false;
                break;
            }
            if (command.equals("2")) {
                if (newSub) {
                    data.getHouses().remove(inputHouse.getId());
                }
                isShowing = true;
                break;
            }
            System.out.println();
        }
    }

    public static boolean loadHouse(String id, HouseManager houseData, User currentUser, String status) {
        if (HouseDetail.checkForHouse(id, houseData) &&
                !houseData.getHouses().get(id).getStatus().equals(status) &&
                !houseData.getHouses().get(id).getStatus().equals("forSaleForRent") &&
                !houseData.getHouses().get(id).getStatus().equals("rented") &&
                houseData.getHouses().get(id).getOwner().getID().equals(currentUser.getID())) {
            return true;
        } else {
            return false;
        }
    }

}