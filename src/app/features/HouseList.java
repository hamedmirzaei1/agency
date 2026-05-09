package app.features;

import data.HouseManager;
import user.User;

public class HouseList {

    public void currentUserHouses(User currentUser, HouseManager houseData) {
        int counter = 1;
        for(String id : houseData.getHouses().keySet()) {
            if(id.equals(currentUser.getID())) {
                System.out.println(counter + ". " + id);
                counter++;
            }
        }
    }
}
