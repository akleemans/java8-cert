package ch.diso.javacert.examples;

import java.util.concurrent.*;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        // How many processors are available?
        int nrProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + nrProcessors);

        ExecutorService service = null; // = Executors.newSingleThreadExecutor();
        try {
            service = Executors.newSingleThreadExecutor();

            // Can start service with Tasks which implement the Runnable interface:
            service.execute(() -> System.out.println("I do something"));
            service.execute(() -> System.out.println("Executing!"));
            service.execute(() -> System.out.println("I am a task."));

            // We can also use the Callable interface, which allows return values & throwing exceptions.
            Future<Long> future = service.submit(() -> {
                Thread.sleep(1);
                return 42L;
            });

            // We can check the Future<> object if the result is available yet:
            System.out.println("ARE WE THERE YET?? " + future.isDone());

            // Now we want the result and can specify how long we're going to wait.
            Long result = future.get(3, TimeUnit.SECONDS);

            // Could also .cancel() it or check if it was cancelled.
            System.out.println("Was Future cancelled? " + future.isCancelled());
            System.out.println("Got result \\o/ : " + result);
        } catch (Exception e) {
            System.out.println("Exception while using ExecutorService.");
        } finally {
            // Will just initiate shutdown, not immediate stop.
            // This will result in no more new tasks being accepted, but finishing the started ones.
            if (service != null) {
                service.shutdown();
            }
        }

        // Scheduled Execution: Own ExecutorService.
        ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();

        // We can now schedule Runnable or Callable tasks.
        scheduledService.schedule(() -> System.out.println("I do something and will arrive last, after 5 seconds :("), 5, TimeUnit.SECONDS);

        Callable<String> greetingTask = () -> "Hi there, I'm an old task from the past!";
        Future<?> scheduledResult = scheduledService.schedule(greetingTask, 1, TimeUnit.SECONDS);

        // Could also schdule at fixed rate (all 5 minutes, regardless if the task is still running)
        // or fixed delay (fixed delay after last task was finished).
        scheduledService.scheduleAtFixedRate(() -> System.out.println("Recurring event!"), 1, 1, TimeUnit.MINUTES);
        scheduledService.scheduleWithFixedDelay(() -> System.out.println("Also a recurring event!"), 1, 1, TimeUnit.MINUTES);

        // Initiating shutdown process for our scheduledService.
        scheduledService.shutdown();

        // Wait for our greetingTask to finish
        while (!scheduledResult.isDone()) {
            Thread.sleep(1000);
            System.out.println("zzZzZzzzzZZzz....");
        }
        try {
            System.out.println("Task finished: " + scheduledResult.get());

        } catch (ExecutionException e) {
            System.out.println("ExecutionException: " + e);
        }

        System.out.println("Finished!");
    }
}
