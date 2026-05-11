package data;

import house.Apartment;
import house.House;
import house.PentHouse;
import house.VillaHouse;
import user.User;

import java.io.*;
import java.util.HashMap;

public class HouseManager {
    private final String HOUSES_FILE_NAME = "Houses.txt";

    private HashMap<String, House> houses = new HashMap<>();

    public HashMap<String, House> getHouses() {
        return houses;
    }

    public void updateHousesFile() {
        try(PrintWriter writer = new PrintWriter(new FileWriter(HOUSES_FILE_NAME))) {
            for(String id : houses.keySet()) {
                writer.print(id); writer.print(", ");
                writer.print(houses.get(id).getName()); writer.print(", ");
                writer.print(houses.get(id).getOwner().getID()); writer.print(", ");
                writer.print(houses.get(id).getStatus()); writer.print(", ");
                writer.print(houses.get(id).getArea()); writer.print(", ");
                writer.print(houses.get(id).getFloor()); writer.print(", ");
                writer.print(houses.get(id).getNumberOfFloors()); writer.print(", ");
                writer.print(houses.get(id).getRoomNumbers()); writer.print(", ");
                writer.print(houses.get(id).getBathroomNumbers()); writer.print(", ");
                writer.print(houses.get(id).getRegion()); writer.print(", ");

                switch (houses.get(id).getName()) {
                    case "VillaHouse":
                        writer.print(((VillaHouse)houses.get(id)).getYardArea());
                        break;
                    case "Apartment":
                        writer.print(((Apartment)houses.get(id)).getNumberOfUnits()); writer.print(", ");
                        writer.print(((Apartment)houses.get(id)).getUnitNumber());
                        break;
                    case "PentHouse":
                        writer.print(((PentHouse)houses.get(id)).getTerraceArea());
                        break;
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadHousesFile(UserManager userData) {
        File file = new File(HOUSES_FILE_NAME);
        if(!file.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;


            while((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                try {

                    String id = parts[0];
                    String houseType = parts[1];
                    User owner = userData.getIdToUser().get(parts[2]);
                    String status = parts[3];
                    int area = Integer.parseInt(parts[4]);
                    int floor = Integer.parseInt(parts[5]);
                    int numberOfFloors = Integer.parseInt(parts[6]);
                    int roomNumbers = Integer.parseInt(parts[7]);
                    int bathroomNumbers = Integer.parseInt((parts[8]));
                    int region = Integer.parseInt(parts[9]);

                    switch (houseType) {
                        case "VillaHouse":
                            int yardArea = Integer.parseInt(parts[10]);
                            houses.put(id, new VillaHouse(id, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, yardArea));
                            break;
                        case "Apartment":
                            int numberOfUnits = Integer.parseInt(parts[10]);
                            int unitNumber = Integer.parseInt(parts[11]);
                            houses.put(id, new Apartment(id, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, numberOfUnits, unitNumber));
                            break;
                        case "PentHouse":
                            int terraceArea = Integer.parseInt(parts[10]);
                            houses.put(id, new PentHouse(id, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, region, terraceArea));
                            break;
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
