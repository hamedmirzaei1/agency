package house;

import data.HouseManager;
import user.User;

public class PentHouse extends House{
    private int terraceArea;

    public PentHouse(HouseManager data, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone, int terraceArea) {
        super(data, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, zone);
        this.terraceArea = terraceArea;
    }

    public PentHouse(String id, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone, int terraceArea) {
        super(id, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, zone);
        this.terraceArea = terraceArea;
    }

    private final double luxuryCoefficient = 1.5;
    private final int terracePricePerMeter = 80;

    @Override
    public int getPrice() {
        return (int)((getBasePrice() * luxuryCoefficient) + (terraceArea * terracePricePerMeter));
    }

    @Override
    public String getName() {
        return "PentHouse";
    }

    public int getTerraceArea() {
        return terraceArea;
    }
}
