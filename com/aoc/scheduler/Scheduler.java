package com.aoc.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface Scheduler {
    public Future<Void> enqueue(Callable<Void> callable);
    public void setDelay(int delay);
}
