package ch.diso.javacert.exercises.ocp02;

public class CustomerReport {

    private Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void generateReport() {

        System.out.println("\t\t\tCUSTOMERS REPORT");
        System.out.println("\t\t\t================");

        for (int custIdx = 0; custIdx < bank.getNumOfCustomers(); custIdx++) {
            Customer customer = bank.getCustomer(custIdx);

            System.out.println();
            System.out.println("Customer: " + customer.getLastName() + ", " + customer.getFirstName());

            for (int acctIdx = 0; acctIdx < customer.getNumOfAccounts(); acctIdx++) {
                Account account = customer.getAccount(acctIdx);
                System.out.println("    " + account);
            }
        }
    }
}