package contract;

import house.House;
import user.User;

public abstract class Trade {
    public static boolean trade(User seller, User buyer, House house, int price) {
        if(buyer.getBudget() > price) {
            buyer.changeBudget(-price);
            seller.changeBudget(+price);
            house.setOwner(buyer);
            house.setStatus("none");
            return true;
        } else {
            return false;
        }
    }

    public static void rent(House house) {
        house.setStatus("rented");
    }

    public static boolean cancelRent(RentContract contract) {
        int penalty = contract.getMonthlyRent() * 6;
        if (penalty < contract.getLandlord().getBudget()) {
            contract.getLandlord().changeBudget(-penalty);
            return true;
        } else {
            return false;
        }
    }
}