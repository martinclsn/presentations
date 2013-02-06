package com.teliasonera;

import java.util.ArrayList;
import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import com.ecyrd.speed4j.StopWatch;

public class Slf4jProfilerTest {

    private static final int MILLIS = 20;

    private static final int NO_OF_THREADS = 20;

    private static final int NO_OF_INVOCATIONS = 5000;

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    Logger logger = LoggerFactory.getLogger(getClass());

    org.apache.log4j.Logger loggerLog4j = org.apache.log4j.Logger.getLogger(getClass());

    @Before
    public void before() {
    }

    @Test
    @Ignore
    @PerfTest(invocations = NO_OF_INVOCATIONS, threads = NO_OF_THREADS)
    public void slf4jProfiler() throws Exception {
        final Profiler profiler = new Profiler("Slf4jProfiler");
        profiler.setLogger(logger);
        doSomething();
        profiler.stop().log();
    }

    @Test
    @PerfTest(invocations = NO_OF_INVOCATIONS, threads = NO_OF_THREADS)
    public void perf4j() throws Exception {
        final Slf4JStopWatch stopWatch = new Slf4JStopWatch("perf4j", logger);
        doSomething();
        stopWatch.stop();
    }

    @Test
    @PerfTest(invocations = NO_OF_INVOCATIONS, threads = NO_OF_THREADS)
    public void onlylog4j() throws Exception {
        final long t0 = System.currentTimeMillis();
        doSomething();
        final long t1 = System.currentTimeMillis();
        loggerLog4j.debug("start[" + t1 + "] time[" + (t1 - t0) + "] tag[log4j]");
    }

    @Test
    @PerfTest(invocations = NO_OF_INVOCATIONS, threads = NO_OF_THREADS)
    public void onlySlf4j() throws Exception {
        final long t0 = System.currentTimeMillis();
        doSomething();
        final long t1 = System.currentTimeMillis();
        logger.debug("start[{}] time[{}] tag[{}]", t1, t1 - t0, "slf4j");
    }

    @Test
    @Ignore
    @PerfTest(invocations = NO_OF_INVOCATIONS, threads = NO_OF_THREADS)
    public void speed4j() throws Exception {
        final StopWatch stopWatch = new StopWatch("speed4j");
        doSomething();
        stopWatch.stop();
    }

    private boolean doSomething() throws InterruptedException {
        // Thread.sleep(MILLIS);
        int sum = 0;
        final List<String> strings = new ArrayList<String>();
        for (int i = 0; i < 30000; i++) {
            strings.add(String.valueOf(sum) + String.valueOf(i));
            sum = sum + i;
        }
        return sum > strings.size();

    }

}
