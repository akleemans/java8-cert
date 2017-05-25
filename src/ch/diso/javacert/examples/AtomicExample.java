package ch.diso.javacert.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicExample {

    private static Long longCounter = 0L;

    public static void main(String[] args) {
        // Define an AtomicLong, example for atomic read/write
        AtomicLong atomicLong = new AtomicLong();
        // atomic operations on AtomicLong
        System.out.println("atomicLong (getAndIncrement): " + atomicLong.getAndIncrement());
        System.out.println("atomicLong (incrementAndGet): " + atomicLong.incrementAndGet());

        // define unsafe runnable: value of longCounter will be undetermined in the end!
        Runnable adder = () -> {
            try {
                Thread.sleep((int) (Math.random() * 100));
                // Getting value. We don't know which value longCounter has at this point!
                Long myLong = longCounter;
                Thread.sleep((int) (Math.random() * 100));
                // Adding one and saving. We have no clue how many other Threads already wrote
                // since we to the number or if other Threads will overwrite the value after us.
                myLong += 1;
                longCounter = myLong;
            } catch (InterruptedException e) {
            }
        };

        // prepare a service & start Threads
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(adder);
        }
        service.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Counter is now: " + longCounter); // 2? 4? Can really be anything between 1 and 10


        // Now for a synchronized version!
        // We need a object which shouldn't change to put a "synchronized" lock on.
        final String synchObject = "foobar";

        Runnable synchronizedAdder = () -> {
            synchronized (synchObject) {
                try {
                    Thread.sleep((int) (Math.random() * 100));
                    Long myLong = longCounter;
                    myLong += 1;
                    longCounter = myLong;
                } catch (InterruptedException e) {
                }
            }
        };

        // new try with our new synchronizedAdder
        longCounter = 0L;
        service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(synchronizedAdder);
        }
        service.shutdown();

        // now wait longer, as this really will take longer (in the worst case more than 10*100ms)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("Synchronized counter is: " + longCounter); // will be 10
    }
}
