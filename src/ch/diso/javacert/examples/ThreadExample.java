package ch.diso.javacert.examples;

public class ThreadExample {

    public static void main(String[] args) {
        // creating a Runnable
        Runnable runnable = () -> {
            System.out.println("Hello Thread!");
        };

        // Preparing Thread with Runnable argument. This is one way to use Threads directly,
        // the other is the method below, by extending Thread and overriding run().
        Thread thread = new Thread(runnable);

        // poll active Threads: activeCount()
        System.out.println("Active thread count before starting: " + Thread.activeCount());
        thread.start();
        System.out.println("Active thread count after starting: " + Thread.activeCount());

        // Thread has some static methods, like .sleep(ms)
        System.out.print("Waiting");
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Being interrupted! :(");
            }
        }

        // defining our own Thread
        Thread customThread = new CustomThread();
        System.out.println("\nCreated custom Thread: " + customThread);

        // direct execution (not asynchronously!) with .run(), will run in same Thread as main program
        customThread.run();
    }
}

class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("Doing something...");
    }
}
