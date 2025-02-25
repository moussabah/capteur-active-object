package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * atomic diffusion strategy
 */
public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    int counter = 0;
    private List<ObserverDeCapteurAsync> proxies;

    private List<Future<Void>> results = new ArrayList<>();

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
    public boolean isTerminated() {
        for (Future<Void> future : this.results){
            if (!future.isDone()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync) {
        this.proxies.add(observerDeCapteurAsync);
    }

    @Override
    public boolean detach(ObserverDeCapteurAsync observerDeCapteurAsync) {
        return this.proxies.remove(observerDeCapteurAsync);
    }
}
