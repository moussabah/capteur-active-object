package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Atomic diffusion
 * Use for atomic reading form proxy and afficheurs
 */
public class DiffusionSequentielle implements AlgoDiffusion {

    private Capteur capteur;
    private List<ObserverDeCapteurAsync> proxies;
    // Use to copy captor value
    private int value;
    private List<Future<Void>> futures = new ArrayList<>();

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
    public boolean isTerminated() {
        for (Future<Void> f : futures){
            if (!f.isDone()){
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
