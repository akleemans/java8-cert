package ch.diso.javacert.basics;

public class EnumExample2 {

    public static void main(String[] args) {
        // initializing enum
        Building b = Building.CASTLE;
        // after the above line, all enums will be initialized, and the two lines
        // Color will be grey / Color will be brown

        Building b2 = Building.CASTLE;
        // Even if we instantiate another object, the constructor
        // will NOT be called one more time (nothing will be printed because of this line).

        System.out.println(b.name() + ": " + b.builtOf());
        // CASTLE: I'm built of stones!
        System.out.println(b.color); // grey

        b = Building.BARN;
        System.out.println(b.name() + ": " + b.builtOf());
        // BARN: I'm built of wood
        System.out.println(b.color); // brown
    }
}

enum Building {

    CASTLE("grey") {
        @Override
        String builtOf() {
            return "I'm built of stones!";
        }
    },
    BARN("brown") {
        @Override
        String builtOf() {
            return "I'm built of wood";
        }
    };

    // enums can have fields
    public String color;

    // enums can have constructors...
    Building(String color) {
        this.color = color;
        System.out.println("Color will be " + color);
    }

    // ...but no public ones!
    // public Building() { } // WILL NOT COMPILE

    // enums can have methods, even abstract ones, that the enum values implement
    abstract String builtOf();

}