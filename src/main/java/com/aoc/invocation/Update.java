package com.aoc.invocation;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {

    public static int counter = 0;

    // Create Servent
    private final ObserverDeCapteurAsync  observerDeCapteurAsync;
    private final ObserverDeCapteur  observerDeCapteur;

    public Update(ObserverDeCapteurAsync observerDeCapteurAsync, ObserverDeCapteur observerDeCapteur){
        Update.counter++;
        this.observerDeCapteurAsync = observerDeCapteurAsync;
        this.observerDeCapteur = observerDeCapteur;
    }

    @Override
    public Void call() {
        observerDeCapteur.update(this.observerDeCapteurAsync);
        return null;
    }
}
