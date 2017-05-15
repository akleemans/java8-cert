package ch.diso.javacert.examples;

public class SingletonExample {
    public static void main(String[] args) {
        // getting an instance of our MusicManager
        MusicManager musicManager = MusicManager.getInstance();
        while (true) {
            // changing volume through provided method, the returned boolean will notify us if operation succeeded
            if (musicManager.turnUp()) {
                System.out.println("TURN DOWN FOR WHAT!!1!");
            } else {
                System.out.println("Volume is already at max :(");
                break;
            }
        }
        System.out.println("Volume is now " + musicManager.getVolume());
    }
}

// our MusicManager which should be a Singleton throughout the application
class MusicManager {
    private static final int MAX_VOLUME = 10;

    private int volume;

    // Marking Constructor as private so no other objects can be created.
    // Also, no subclass can extend MusicManager because no Constructor would be accessible.
    private MusicManager() {
        // initializing volume
        volume = 5;
    }

    // creating an instance at initialization
    private static final MusicManager instance = new MusicManager();

    public static MusicManager getInstance() {
        return instance;
    }

    public synchronized int getVolume() {
        return volume;
    }

    // turn up music if below max, return if it was successful
    public synchronized boolean turnUp() {
        if (volume < MAX_VOLUME) {
            volume++;
            return true;
        } else {
            return false;
        }
    }
}