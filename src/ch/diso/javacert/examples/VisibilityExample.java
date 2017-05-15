package ch.diso.javacert.examples;

public class VisibilityExample {

    public static void main(String[] args) {
        // p has - after the line below - only the members of 'Professional' available,
        // unless it is explicitly cast to "(Banker) p".
        Professional p = new Banker();
        // p.announceSalary(); does not compile - announceSalary() has private access in 'Professional'
        p.doWork();
    }
}

// The class definitions below are only allowed because they're not public,
// only one public class/interface allowed, "there must be at most one [top level public] type per compilation unit."

abstract class Professional {
    private void announceSalary() {
        System.out.println("I earn money!");
    }

    abstract void doWork();
}

class Banker extends Professional {
    public void announceSalary() {
        System.out.println("I earn alot of money!");
    }

    public void doWork() {
        System.out.println("Counting other people's money...");
    }
}