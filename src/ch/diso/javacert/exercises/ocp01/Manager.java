package ch.diso.javacert.exercises.ocp01;

public class Manager extends Employee {
    private String deptName;

    public Manager(int empId, String name, String ssn, double salary, String deptName) {
        super(empId, name, ssn, salary);
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    @Override
    public String toString() {
        // take toString() from Employee and add department
        return super.toString() + "\nDepartment:          " + this.deptName;
    }
}