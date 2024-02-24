package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.scheduler.SchedulerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class DiffusionSequentielle implements AlgoDiffusion{

    private Capteur capteur;
    private List<ObserverDeCapteurAsync> proxies;
    private int value;
    private List<Future<Void>> futures = new ArrayList<>();
    private int nbPool = 1;

    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs) {
        this.capteur = capteur;
        this.proxies = observerDeCapteurAsyncs;
    }

    @Override
    public void execute() {
        this.capteur.increment();
        boolean isReady = true;
        for (Future<Void> f : futures){
            if (!f.isDone()){
                isReady = false;
                break;
            }
        }

        if (isReady){
            this.futures.clear();
            this.value = this.capteur.getValue();
             this.proxies.forEach(proxy -> {
                 this.futures.add(proxy.update(proxy));
            });
        }
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public Scheduler getScheduler() {
        return new SchedulerImpl(this.nbPool);
    }

    @Override
    public void setNbPool(int pool) {
        this.nbPool = pool;
    }

    @Override
    public boolean isTerminated() {
        for (Future<Void> f : futures){
            if (!f.isDone()){
                return false;
            }
        }
        return true;
    }
}
