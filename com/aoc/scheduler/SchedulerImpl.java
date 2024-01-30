package com.aoc.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SchedulerImpl implements Scheduler {

    ExecutorService executorService;

    SchedulerImpl(){
        this.executorService = Executors.newFixedThreadPool(1);
    }

    @Override
    public Future<Integer> enqueue(Callable<Integer> callable) {
        return this.executorService.submit(callable);
    }
}
