package contract;

import data.UserManager;
import house.House;
import user.User;

public abstract class TradeTools {
    public static boolean trade(User seller, User buyer, House house, int price, UserManager userData) {
        if (seller.getID().equals("superuser")) {
            if (buyer.getBudget() > price) {
                buyer.changeBudget(-price);
                house.setOwner(buyer);
                house.setStatus("none");
                return true;
            } else {
                System.out.println();
                System.out.println("not enought budget");
                System.out.println();
                return false;
            }
        }

        seller = userData.getIdToUser().get(seller.getID());
        if(buyer.equals(seller)) {
            System.out.println("\nyou can't purchase your own house\n");
            return false;
        }
        if(buyer.getBudget() > price) {
            buyer.changeBudget(-price);
            seller.changeBudget(+price);
            house.setOwner(buyer);
            house.setStatus("none");
            return true;
        } else {
            System.out.println();
            System.out.println("not enough budget");
            System.out.println();
            return false;
        }
    }
    public static void fastTrade(User superUser, User seller, House house, int price) {
        price = (int)(price * 0.9);
        seller.changeBudget(price);
        house.setOwner(superUser);
        house.setStatus("forSaleForRent");
    }

    public static boolean rentTransaction(User landlord, User secondOne, int price) {
        if(price < secondOne.getBudget()) {
            secondOne.changeBudget(-price);
            landlord.changeBudget(+price);
            return true;
        } else {
            return false;
        }
    }

    public static boolean cancelRent(RentContract contract, UserManager userData) {
        int penalty = getCancelPenalty(contract.getHouse());
        if (penalty < contract.getSecondOne().getBudget()) {
            userData.getIdToUser().get(contract.getSecondOne().getID()).changeBudget(-penalty);
            userData.getIdToUser().get(contract.getLandlord().getID()).changeBudget(penalty);
            contract.setIsValid("no");
            contract.getHouse().setStatus("none");
            if (contract.getLandlord().getID().equals("superuser")) {
                contract.getHouse().setStatus("forSaleForRent");
            }
            return true;
        } else {
            return false;
        }
    }
    public static int getCancelPenalty(House house) {
        return house.getMonthlyRentPrice() * 6;
    }
}