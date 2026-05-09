package house;

import data.HouseManager;
import user.User;

public class Apartment extends House{
    private int numberOfUnits;
    private int unitNumber;

    public Apartment(HouseManager data, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone, int numberOfUnits, int unitNumber) {
        super(data, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, zone);
        this.numberOfUnits = numberOfUnits;
        this.unitNumber = unitNumber;
    }

    public Apartment(String id, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone, int numberOfUnits, int unitNumber) {
        super(id, owner, status, area, floor, numberOfFloors, roomNumbers, bathroomNumbers, zone);
        this.numberOfUnits = numberOfUnits;
        this.unitNumber = unitNumber;
    }

    @Override
    public int getPrice() {
        return (int)(getBasePrice() * ((1 + (0.03 * getRoomNumbers())) * (1 + (0.01 * super.getFloor()))));
    }


    @Override
    public String getName() {
        return "Apartment";
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public int getUnitNumber() {
        return unitNumber;
    }
}
