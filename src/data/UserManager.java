package data;

import user.User;

import java.io.*;
import java.util.HashMap;

public class DataManager {
    private final String USERS_FILE_NAME = "Users.txt";
    private final String HOUSES_FILE_NAME = "Houses.txt";
    private final String CONTRACTS_FILE_NAME = "Contracts.txt";

    private HashMap<String, String> userNameToID = new HashMap<>();
    private HashMap<String, User> idToUser = new HashMap<>();

    public HashMap<String, User> getIdToUser() {
        return idToUser;
    }

    public HashMap<String, String> getUserNameToID() {
        return userNameToID;
    }

    public void saveUser(User user) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE_NAME, true))) {
            writer.print(user.getID());
            writer.print(", ");
            writer.print(user.getUserName());
            writer.print(", ");
            writer.print(user.getHashedPassword());
            writer.print(", ");
            writer.print(user.getName());
            writer.print(", ");
            writer.print(user.getBudget());
            writer.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadUser() {
        File file = new File(USERS_FILE_NAME);
        if(!file.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            String id = "";
            String name = "";
            String userName = "";
            String hashedPassword = "";
            int budget = 0;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if(parts.length == 5) {
                    id = parts[0];
                    userName = parts[1];
                    hashedPassword = parts[2];
                    name = parts[3];
                    budget = Integer.parseInt(parts[4]);

                    idToUser.put(id, new User(id, name, userName, hashedPassword, budget));
                    userNameToID.put(userName, id);
                }

            }

        } catch(IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

}
