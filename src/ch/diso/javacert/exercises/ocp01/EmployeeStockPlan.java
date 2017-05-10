package ch.diso.javacert.exercises.ocp01;

public class EmployeeStockPlan {

    public static int grantStock(Employee emp) {
        if (emp instanceof Director) {
            return 1000;
        } else if (emp instanceof Manager) {
            return 100;
        } else {
            return 10;
        }
    }
}
