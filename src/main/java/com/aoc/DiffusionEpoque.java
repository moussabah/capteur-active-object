package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.scheduler.SchedulerImpl;

import java.util.List;

public class DiffusionEpoque implements AlgoDiffusion{
    private int nbPool = 1;

    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs) {

    }

    @Override
    public void execute() {

    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public Scheduler getScheduler() {
        Scheduler scheduler = new SchedulerImpl(this.nbPool);
        scheduler.setDelay(3000L);
        return scheduler;
    }

    @Override
    public void setNbPool(int pool) {
        this.nbPool = pool;
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
