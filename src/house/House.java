package house;

import user.User;

import java.util.UUID;

public abstract class House {
    private String id;
    private User owner;
    private String status;

    private int area;
    private int roomNumbers;
    private int bathroomNumbers;


    public House(User owner, String status, int area, int roomNumbers, int bathroomNumbers) {
        this.owner = owner;
        this.status = status;
        this.area = area;
        this.roomNumbers = roomNumbers;
        this.bathroomNumbers = bathroomNumbers;
        this.status = status;

        this.id = UUID.randomUUID().toString();
    }

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

    public int getArea() {
        return area;
    }

    public int getRoomNumbers() {
        return roomNumbers;
    }

    public int getBathroomNumbers() {
        return bathroomNumbers;
    }

    public String getId() {
        return id;
    }
}
