package app;

import app.signup.SignUpMenu;
import data.ContractManager;
import data.HouseManager;
import data.UserManager;
import user.User;

public class Session {
    private User currentUser;

    private HouseManager houseData = new HouseManager();
    private UserManager userData = new UserManager();
    private ContractManager contractData = new ContractManager();

    private MainMenu mainMenu = new MainMenu();
    private SignUpMenu signUpMenu = new SignUpMenu();

    public void startSession() {
        userData.loadUsersFile();
        houseData.loadHousesFile(userData);
        contractData.loadContractsFile(houseData, userData);

        signUpMenu.menu(this);
        this.currentUser = signUpMenu.getCurrentUser();

        mainMenu.menu(this);
    }

    public void endSession() {
        updateFiles(this);

        mainMenu.setShowing(false);
        signUpMenu.setShowing(false);
    }

    public static void updateFiles(Session session) {
        session.houseData.updateHousesFile();
        session.userData.updateUsersFile();
        session.contractData.updateContractsFile();
    }

    public UserManager getUserData() {
        return userData;
    }

    public HouseManager getHouseData() {
        return houseData;
    }

    public ContractManager getContractData() {
        return contractData;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
