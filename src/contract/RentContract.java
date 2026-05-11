package contract;

import id.IDKeeper;
import house.House;
import user.User;

import java.time.LocalDate;


public class RentContract extends IDKeeper {
    private String id;
    private House house;
    private User landlord;
    private User secondOne;
    private String deadline;

    private String isValid;

    private int monthlyRent;

    public RentContract(User landlord, User secendOne, House house, int monthlyRent) {
        this.house = house;
        this.landlord = landlord;
        this.secondOne = secendOne;
        this.monthlyRent = monthlyRent;

        this.deadline = LocalDate.now().plusYears(1).toString();
        this.id = super.idGenerator();
        this.isValid = "yes";

        Trade.rent(house);
    }

    public RentContract(String id, House house, User landlord, User secondOne, String deadline, String isValid, int monthlyRent) {
        this.id = id;
        this.house = house;
        this.landlord = landlord;
        this.secondOne = secondOne;
        this.deadline = deadline;
        this.isValid = isValid;
        this.monthlyRent = monthlyRent;
    }

    public String getValidString() {
        return isValid;
    }

    public boolean getValid() {
        if (isValid.equals("yes")) {
            return true;
        } if(isValid.equals("no")) {
            return false;
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public House getHouse() {
        return house;
    }

    public User getLandlord() {
        return landlord;
    }

    public User getSecondOne() {
        return secondOne;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }
}
