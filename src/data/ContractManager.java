package data;

import contract.RentContract;
import house.House;
import user.User;

import java.io.*;
import java.util.HashMap;

public class ContractManager {
    private final String CONTRACTS_FILE_NAME = "Contracts.txt";

    private HashMap<String, RentContract> contracts = new HashMap<>();

    public HashMap<String, RentContract> getContracts() {
        return contracts;
    }

    public void updateContractsFile() {
        try(PrintWriter writer = new PrintWriter(new FileWriter(CONTRACTS_FILE_NAME))) {
            for(String id : contracts.keySet()) {
                writer.print(id); writer.print(", ");
                writer.print(contracts.get(id).getValidString()); writer.print(", ");
                writer.print(contracts.get(id).getHouse().getId()); writer.print(", ");
                writer.print(contracts.get(id).getLandlord().getID()); writer.print(", ");
                writer.print(contracts.get(id).getSecondOne().getID()); writer.print(", ");
                writer.print(contracts.get(id).getDeadline()); writer.print(", ");
                writer.print(contracts.get(id).getMonthlyRent());
                writer.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadContractsFile(HouseManager houseData, UserManager userData) {
        File file = new File(CONTRACTS_FILE_NAME);
        if(!file.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if(parts.length == 7) {
                    String id = parts[0];
                    String isValid = parts[1];
                    House house = houseData.getHouses().get(parts[2]);
                    User landlord = userData.getIdToUser().get(parts[3]);
                    User secondOne = userData.getIdToUser().get(parts[4]);
                    String deadLine = parts[5];
                    int monthlyRent = Integer.parseInt(parts[6]);

                    contracts.put(id, new RentContract(id, house, landlord, secondOne, deadLine, isValid, monthlyRent));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}