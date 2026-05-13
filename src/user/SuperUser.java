package user;

public class SuperUser extends User {
    public SuperUser(int budget) {
        super("superuser", null, null, null, budget);
    }
    @Override
    public void changeBudget(int change) {
        this.budget = budget + change;
    }
}
