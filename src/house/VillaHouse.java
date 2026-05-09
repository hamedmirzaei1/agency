package house;

import data.HouseManager;
import user.User;

public class VillaHouse extends House{
    private int yardArea;

    public final int yardPricePerMeter = 40;
    public final int floorPremium =  500;

    public VillaHouse(HouseManager data, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone, int yardArea) {
        super(data, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, zone);
        this.yardArea = yardArea;
    }

    public VillaHouse(String id, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone, int yardArea) {
        super(id, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, zone);
        this.yardArea = yardArea;
    }

    @Override
    public int getPrice() {
        return (int)(getBasePrice() + (yardArea + yardPricePerMeter) + (getNumberOfFloors() * floorPremium));
    }

    @Override
    public String getName() {
        return "VillaHouse";
    }

    public int getYardArea() {
        return yardArea;
    }

}
