package ch.diso.javacert.exercises.ocp02;

public class CheckingAccount extends Account {

    private final double overDraftLimit;

    public CheckingAccount(double balance) {
        super(balance);
        // initialize overDraftLimit with 0
        overDraftLimit = 0;
    }

    public CheckingAccount(double balance, double overDraftLimit) {
        super(balance);
        this.overDraftLimit = overDraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        // check if withdrawal would be within limits
        if (balance - amount >= overDraftLimit) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Checking Account (with " + overDraftLimit + " over draft limit)";
    }
}