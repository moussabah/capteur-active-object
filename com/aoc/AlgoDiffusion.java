package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;

import java.util.List;
import java.util.concurrent.Future;

public interface AlgoDiffusion {
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs);
    public void execute();

    public int getValue();

    Scheduler getScheduler();
}
