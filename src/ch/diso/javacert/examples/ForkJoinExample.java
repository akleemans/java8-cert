package ch.diso.javacert.examples;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    public static void main(String[] args) throws InterruptedException {

        // Three steps to apply the fork/join framework:
        // 1. Create a ForkJoinTask
        // 2. Create the ForkJoinPool
        // 3. Start the ForkJoinTask

        long from = 0;
        long to = 1_000_000_000;
        long start, end;

        start = System.currentTimeMillis();
        ForkJoinTask<Long> task = new NumberCounter(from, to);
        ForkJoinPool pool = new ForkJoinPool();
        Long sum = pool.invoke(task);
        end = System.currentTimeMillis();

        System.out.println("(Fork/Join) Sum from " + from + " to " + to + ": " + sum);
        System.out.println("(Fork/Join) Time: " + (end - start) + "ms");

        // ----- single threaded -----
        start = System.currentTimeMillis();
        long sum2 = 0;
        for (long i = from; i < to; i++) {
            sum2 += to;
        }
        end = System.currentTimeMillis();
        System.out.println("(Single Thread) Sum from " + from + " to " + to + ": " + sum);
        System.out.println("(Single Thread) Time: " + (end - start) + "ms");

        // Sample output:
        // Fork/Join) Sum from 0 to 1000000000: 500244140625000192
        // (Fork/Join) Time: 390ms
        // (Single Thread) Sum from 0 to 1000000000: 500244140625000192
        // (Single Thread) Time: 506ms

        // A speed improvement of 23%! This is with the threshold below of 500_000.
        // If we set it to a small number like 10_000, the overhead will show
        // and the time is around 1800ms, more than tripled!
    }
}

class NumberCounter extends RecursiveTask<Long> {
    private long from, to;
    private static long threshold = 500_000;

    public NumberCounter(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        // Main routine to decide whether to divide (spawn new sub-processes)
        // or conquer (compute result, base case).
        if (to - from < threshold) {
            System.out.println("Feeling confident, summing up numbers from " + from + " to " + to);
            long sum = 0;
            for (long i = from; i < to; i++) {
                sum += to;
            }
            return sum;
        } else {
            System.out.println("Too much numbers, distributing " + from + " to " + to);
            long middle = from + ((to - from) / 2);
            // prepare a second Task and fork()
            RecursiveTask<Long> secondTask = new NumberCounter(from, middle);
            secondTask.fork();
            // Initialize second task, compute result directly and 'join' result from task before.
            // .join(): Returns the result of the computation when it is done.
            // (Like get(), but without checked exceptions).
            return new NumberCounter(middle, to).compute() + secondTask.join();
        }
    }
}