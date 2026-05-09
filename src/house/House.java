package house;

import data.HouseManager;
import id.IDKeeper;
import user.User;


public abstract class House extends IDKeeper {
    private String id;
    private User owner;
    private String status;

//    private boolean hasRent;

    private int area;
    private int floor;
    private int numberOfFloors;
    private int roomNumbers;
    private int bathroomNumbers;
    private int region;

    public final int  basePricePerMeter = 100;
    public final double[] regionCoefficient = {1.8, 1.4, 1.1, 0.8};
    public final double rentRate = 0.004;


    public House(HouseManager data, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone) {
        this.owner = owner;
        this.status = status;
        this.area = area;
        this.floor = floor;
        this.numberOfFloors = numberOfFloors;
        this.roomNumbers = roomNumbers;
        this.bathroomNumbers = bathroomNumbers;
        this.status = status;
        this.region = zone;
        this.id = super.idGenerator();
        data.getHouses().put(id, this);
    }

    public House(String id, User owner, String status, int area, int floor, int numberOfFloors, int roomNumbers, int bathroomNumbers, int zone) {
        this.owner = owner;
        this.status = status;
        this.area = area;
        this.floor = floor;
        this.numberOfFloors = numberOfFloors;
        this.roomNumbers = roomNumbers;
        this.bathroomNumbers = bathroomNumbers;
        this.status = status;
        this.region = zone;
        this.id = id;
//        data.getHouses().put(id, this);
    }


    protected double getBasePrice() {
        return area * basePricePerMeter * regionCoefficient[region - 1];
    }
    public abstract int getPrice();
    public int getMonthlyRentPrice() {
        return (int)(getPrice() * rentRate);
    }
    public abstract String getName();


    public String getOwnerName() {
        return owner.getName();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public int getArea() {
        return area;
    }

    public int getRoomNumbers() {
        return roomNumbers;
    }

    public int getBathroomNumbers() {
        return bathroomNumbers;
    }

    public int getRegion() {
        return region;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}
