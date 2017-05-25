package ch.diso.javacert.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        // "The CyclicBarrier class allows us to perform complex, multi-threaded tasks,
        // while all threads stop and wait at logical barriers."

        // Let's build some Robots with our RobotFactory.
        // Preparing three CyclicBarriers for each step, suppose we have 3 production lines available:
        CyclicBarrier caseBuilding = new CyclicBarrier(3, () -> System.out.println("cases built!"));
        CyclicBarrier circuitBuilding = new CyclicBarrier(3, () -> System.out.println("circuits built!"));
        CyclicBarrier programming = new CyclicBarrier(3, () -> System.out.println("robots programmed!"));

        // Initializing Robot Factory
        RobotFactory robotFactory = new RobotFactory();

        ExecutorService service = null;
        try {
            // Prepare ThreadPool with 3 Threads which will run 5 times, we want 15 Robots to be produced
            service = Executors.newFixedThreadPool(3);
            for (int i = 0; i < 15; i++) {
                // Perform tasks with three cyclic barriers.
                service.submit(() -> robotFactory.performTask(caseBuilding, circuitBuilding, programming));
            }
        } finally {
            // shutdown ExecutorService
            if (service != null) {
                service.shutdown();
            }
        }
    }
}

class RobotFactory {

    public void performTask(CyclicBarrier caseBuilding, CyclicBarrier circuitBuilding, CyclicBarrier programming) {
        try {
            // Perform build steps, all in parallel, until the barrier is reached.
            // await() will only return if the necessary amount of "parties" has reached
            // the barrier, then it will be "broken" and start filling up (queueing tasks) for the next wave
            performStep("building case...");
            caseBuilding.await();
            performStep("building circuits...");
            circuitBuilding.await();
            performStep("programming...");
            programming.await();
            performStep("finished building robot!");
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Exception!");
        }
    }

    private void performStep(String step) {
        System.out.println("Performing step: " + step);
    }
}