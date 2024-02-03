package com.aoc.scheduler;

import java.util.concurrent.*;

public class SchedulerImpl implements Scheduler {

    ScheduledExecutorService executorService;
    private int delay = 0;

    SchedulerImpl(ScheduledExecutorService scheduler){
        this.executorService = scheduler;
    }

    @Override
    public Future<Integer> enqueue(Callable<Integer> callable) {
        return this.executorService.schedule(callable, this.delay, TimeUnit.SECONDS);
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
