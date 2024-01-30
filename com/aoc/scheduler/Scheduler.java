package com.aoc.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface Scheduler {
    public Future<Integer> enqueue(Callable<Integer> callable);
}
