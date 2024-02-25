package com.aoc.invocation;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {

    // Create Servent
    private final ObserverDeCapteurAsync  observerDeCapteurAsync;
    private final ObserverDeCapteur  observerDeCapteur;

    public Update(ObserverDeCapteurAsync observerDeCapteurAsync, ObserverDeCapteur observerDeCapteur){
        this.observerDeCapteurAsync = observerDeCapteurAsync;
        this.observerDeCapteur = observerDeCapteur;
    }

    @Override
    public Void call() throws InterruptedException {
        Thread.sleep(1500L);
        observerDeCapteur.update(this.observerDeCapteurAsync);
        return null;
    }
}
