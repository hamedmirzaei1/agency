package contract;

import house.House;
import user.User;

public abstract class Trade {
    public static void trade(User seller, User buyer, House house, int price) {
        if(buyer.getBudget() > price) {
            buyer.changeBudget(-price);
            seller.changeBudget(+price);
            house.setOwner(buyer);
            house.setStatus(null);

        } //todo
    }
}