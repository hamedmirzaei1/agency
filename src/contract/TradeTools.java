package contract;

import house.House;
import user.User;

public abstract class TradeTools {
    public static boolean trade(User seller, User buyer, House house, int price) {
        if(buyer.getBudget() > price && house.getStatus().equals("forSale") && !buyer.equals(seller)) {
            buyer.changeBudget(-price);
            seller.changeBudget(+price);
            house.setOwner(buyer);
            house.setStatus("none");
            return true;
        } else {
            return false;
        }
    }

    public static boolean cancelRent(RentContract contract) {
        int penalty = getCancelPenalty(contract.getHouse());
        if (penalty < contract.getSecondOne().getBudget()) {
            contract.getSecondOne().changeBudget(-penalty);
            contract.setIsValid("no");
            contract.getHouse().setStatus("none");
            return true;
        } else {
            return false;
        }
    }
    public static int getCancelPenalty(House house) {
        return house.getMonthlyRentPrice() * 6;
    }
}