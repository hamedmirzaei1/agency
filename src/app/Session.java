package app;

import app.signup.SignUpMenu;
import data.HouseManager;
import data.UserManager;
import user.User;

public class Session {
    private User currentUser;

    private HouseManager houseData = new HouseManager();
    private UserManager userData = new UserManager();

    private MainMenu mainMenu = new MainMenu();
    private SignUpMenu signUpMenu = new SignUpMenu();

    public void startSession() {
        userData.loadUsersFile();
        houseData.loadHousesFile(userData);

        signUpMenu.menu(this);
        this.currentUser = signUpMenu.getCurrentUser();

        mainMenu.menu(this);
    }

    public void endSession() {
        houseData.updateHousesFile();
        userData.updateUsersFile();

        mainMenu.setShowing(false);
        signUpMenu.setShowing(false);
    }

    public UserManager getUserData() {
        return userData;
    }

    public HouseManager getHouseData() {
        return houseData;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
