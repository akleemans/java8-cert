package ch.diso.javacert.exercises.ocp02;

import java.util.Date;

public class TimeDepositAccount extends Account {

    private final Date maturityDate;

    public TimeDepositAccount(double balance, Date maturityDate) {
        super(balance);
        this.maturityDate = maturityDate;
    }

    public boolean withdraw(double amount) {
        Date today = new Date();
        if (today.after(maturityDate)) {
            if (amount <= balance) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Time Deposit Account " + maturityDate;
    }

}