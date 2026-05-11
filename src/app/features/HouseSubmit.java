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

    public void menu(String status, Scanner sc, HouseManager data, User currentUser) {
        isShowing = true;
        while(isShowing) {
            try {
                House inputHouse;

                System.out.println("Choose the house type:");
                System.out.println("1. Apartment");
                System.out.println("2. PentHouse");
                System.out.println("3. VillaHouse");
                System.out.println();
                System.out.println("4. Back");
                System.out.println("==================================");
                System.out.print("CHOOSE AN OPTION: ");
                String command = sc.nextLine();
                System.out.println();

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
                        System.out.println("NOT VALID OPTION");
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

                        inputHouse = new Apartment(data, currentUser, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, numberOfUnits, unitNumber);
                        confirmSubmit(sc, inputHouse, data);
                        break;
                    case "2":
                        System.out.print("Enter terrace area: ");
                        int terraceArea = Integer.parseInt(sc.nextLine());

                        inputHouse = new PentHouse(data, currentUser, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, terraceArea);
                        confirmSubmit(sc, inputHouse, data);
                        break;
                    case "3":
                        System.out.print("Enter yard area: ");
                        int yardArea = Integer.parseInt(sc.nextLine());

                        inputHouse = new VillaHouse(data, currentUser, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, yardArea);
                        confirmSubmit(sc, inputHouse, data);
                        break;
                }

                System.out.println();

            } catch (Exception e) {
                System.out.println("Input is not valid");
                System.out.println();
            }
        }
    }

    private void confirmSubmit(Scanner sc, House inputHouse, HouseManager data) {
        System.out.println();

        if (inputHouse.getStatus().equals("forSale")) {
            System.out.println("The house price will be: ");
            System.out.print("$" + inputHouse.getPrice());
        }
        if (inputHouse.getStatus().equals("forRent")) {
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
                data.updateHousesFile();

                System.out.println();
                System.out.println("House was successfully added ");
                isShowing = false;
                break;
            }
            if (command.equals("2")) {
                data.getHouses().remove(inputHouse.getId());
                isShowing = true;
                break;
            }
            System.out.println();
        }
    }
}
