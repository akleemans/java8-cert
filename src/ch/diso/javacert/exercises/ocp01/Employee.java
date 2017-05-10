package ch.diso.javacert.exercises.ocp01;

import java.text.NumberFormat;

public class Employee {

    private int empId;
    private String name;
    private String ssn;
    private double salary;

    public Employee(int empId, String name, String ssn, double salary) {
        this.empId = empId;
        this.name = name;
        this.ssn = ssn;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
        }
    }

    public void raiseSalary(double increase) {
        if (increase > 0) {
            salary += increase;

        }
    }

    @Override
    public String toString() {
        return "\nEmplyee type:        " + this.getClass().getSimpleName() +
                "\nEmployee ID:         " + this.getEmpId() +
                "\nEmployee Name:       " + this.getName() +
                "\nEmployee SSN:        " + this.getSsn() +
                "\nEmployee Salary:     " + NumberFormat.getCurrencyInstance().format(this.getSalary()) +
                "\nStock options:       " + EmployeeStockPlan.grantStock(this);
    }
}