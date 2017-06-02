package ch.diso.javacert.examples;

import java.io.*;

public class ObjectStreamExample {

    public static void main(String[] args) {
        // Prepare an object to serialize (instance of class below)
        Human adi = new Human("Adi", 29L);
        System.out.println("Human 'Adi' before serializing: " + adi);

        // Prepare File to write & serialize object to file
        String filename = "human.data";
        File dataFile = new File(filename);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));) {
            objectOutputStream.writeObject(adi);
            System.out.println("Successfully written to file.");
        } catch (IOException e) {
            System.out.println("Could not write to file!");
            throw new IllegalStateException("Cant write to file.");
        }

        // Now read and deserialize object
        Object object = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));) {
            object = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File or class not found.");
        }

        // Magic: Java already knows if object is an instance of Human. But how?
        // Implicit checking for null is already included, instanceof would return false on null.
        if (object instanceof Human) {
            Human human = (Human) object;

            // Print deserialized human. Only thing missing will be age, the rest was either saved or set on class (static SPECIES).
            System.out.println("Deserialized human from file: " + human);
        } else {
            System.out.println("Read file and got something different than a Human...");
        }
    }
}

// Serializable is a marker interface, no methods to be implemented.
class Human implements Serializable {

    // Add a serial id as "class version".
    // Increment it if class definition changes (e.g. new or differen members)
    static final long serialVersionUID = 1L;

    // Marking age as transient, will not be serialized
    private transient long age;

    // Name will be written to file.
    private String name;

    // Static members will not be serialized.
    // But because we already constructed a object with the constructor which makes this 'human',
    // all other instances can be queried and will return the same value (also on deserialized object).
    private static String SPECIES = "???";

    // Will also be serialized.
    private final Boolean likesChocolate;

    public Human(String name, long age) {
        this.name = name;
        this.age = age;

        // let's set this to a default
        this.likesChocolate = true;
        SPECIES = "human";
    }

    @Override
    public String toString() {
        return "age: " + age +
                ", name: " + name +
                ", species: " + SPECIES +
                ", likesChocolate: " + likesChocolate;
    }

}