package ch.diso.javacert.exercises.ocp01;

import java.util.Arrays;

public class EmployeeTest {

    public static void main(String[] args) {
        Employee eng = new Engineer(101, "Arno Hofstetter", "012-34-5678", 120_345.27);
        Employee mgr = new Manager(207, "Daniel Meienberg", "054-12-2367", 109_501.36, "Sales");
        Employee adm = new Admin(304, "Gisela Kaiser", "108-23-6509", 75_002.34);
        Employee dir = new Director(12, "Daniel Senften", "099-45-2340", 120_567.36, "Corporate", 100_000.00);

        // Printing all employees, depending on type it will print more info,
        // for example department for managers or budget for directors
        Arrays.asList(eng, mgr, adm, dir).forEach(System.out::println);

        System.out.println("\nTesting raiseSalary and setName on Manager:");
        mgr.setName("Peter Vögeli");
        mgr.raiseSalary(10000);
        System.out.println(mgr);
    }
}