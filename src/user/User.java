package user;


import id.IDKeeper;

public class User extends IDKeeper {
    private String id;
    private String name;
    private String userName;
    private String hashedPassword;

    private int budget;

    public User(String userName, String hashedPassword, String name) {
        this.id = super.idGenerator();
        this.name = name;
        this.userName = userName;
        this.hashedPassword = hashedPassword;
    }

    public User(String id, String name, String userName, String hashedPassword, int budget) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.budget = budget;
    }

    public String getUserName() {
        return userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getBudget() {
        return budget;
    }

    public void changeBudget(int change) {
        if(budget + change > 0) {
            budget = budget + change;
        } // todo
    }

}
