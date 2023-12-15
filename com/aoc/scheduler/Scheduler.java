package com.aoc.scheduler;

import java.util.concurrent.Callable;

public interface Scheduler {
    void enqueue(Callable<Void> callable);
}
