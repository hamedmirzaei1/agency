import app.MainMenu;
import app.Session;
import data.DataManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DataManager appData = new DataManager();
        Session currentSession = new Session();
        MainMenu a = new MainMenu();

        currentSession.loginMenu(sc, appData);

        a.startMenu(sc, currentSession.getCurrentUser());

    }
}
