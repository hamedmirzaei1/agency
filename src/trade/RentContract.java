package trade;

import house.House;
import user.User;

import java.util.UUID;

public class RentContract {
    private String id;
    private House house;
    private User firstOne;
    private User secondOne;
    private String deadline;

    private int monthlyRent;

    public RentContract(User firstOne, User secendOne, House house, int monthlyRent, String deadline) {
        this.house = house;
        this.firstOne = firstOne;
        this.secondOne = secendOne;
        this.monthlyRent = monthlyRent;
        this.deadline = deadline;

        this.id = UUID.randomUUID().toString();

        house.setStatus(null);
    }
    public void cancelContract() {
        int penalty = monthlyRent * 6;
        if (penalty < firstOne.getBudget()) {
            firstOne.changeBudget(-penalty);
        }
    }
}
