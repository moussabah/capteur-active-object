package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.scheduler.SchedulerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    int counter = 0;
    private List<ObserverDeCapteurAsync> proxies;

    private List<Future<Void>> results = new ArrayList<>();

    private int nbPool = 1;

    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> proxies) {
        this.capteur = capteur;
        this.proxies = proxies;
    }

    @Override
    public void execute() {
        this.capteur.increment();
        this.proxies.forEach(proxy -> {
            this.results.add(proxy.update(proxy));
        });
        for (Future<Void> res : results){
            while (!res.isDone()){}
        }
        this.results.clear();
    }

    @Override
    public int getValue() {
        return this.capteur.getValue();
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
        return this.results.isEmpty();
    }
}
