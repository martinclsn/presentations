package se.guava;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Uninterruptibles;

public class MovieService {
    public void sleep10() {

        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

        // try {
        // Thread.sleep(10000);
        // } catch (final InterruptedException e) {
        // // Ignore
        // }

    }

    public void measure() {
        long start = System.currentTimeMillis();
        doSomething();
        long elapsedMillis = System.currentTimeMillis() - start;
        start = elapsedMillis + 0;

        final Stopwatch stopwatch = new Stopwatch().start();
        doSomething();
        elapsedMillis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(elapsedMillis);
    }

    private void doSomething() {
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

    }

}
