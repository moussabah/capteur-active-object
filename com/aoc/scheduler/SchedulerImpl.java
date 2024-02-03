package com.aoc.scheduler;

import java.util.concurrent.*;

public class SchedulerImpl implements Scheduler {

    ScheduledExecutorService executorService;
    private int delay = 0;

    public SchedulerImpl(int nbPool){
        this.executorService = Executors.newScheduledThreadPool(nbPool);
    }

    @Override
    public Future<Void> enqueue(Callable<Void> callable) {
        return this.executorService.schedule(callable, this.delay, TimeUnit.SECONDS);
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
