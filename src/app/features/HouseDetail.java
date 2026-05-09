package app.features;

import data.HouseManager;

public class DetailMenu {
    public static boolean printDetail(String id, HouseManager houseData) {
        id = id.trim();

        if(houseData.getHouses().containsKey(id)) {
            System.out.println(houseData.getHouses().get(id).getDescription());
            System.out.println();
            return true;
        } else {
            return false;
        }
    }
}
