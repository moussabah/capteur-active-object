package com.aoc.invocation;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {

    public static int counter = 0;

    // Create Servent
    private final ObserverDeCapteur afficheur;
    private final ObserverDeCapteurAsync  observerDeCapteurAsync;

    public Update(ObserverDeCapteurAsync observerDeCapteurAsync){
        Update.counter++;
        this.afficheur = new Afficheur("A"+Update.counter);
        this.observerDeCapteurAsync = observerDeCapteurAsync;
    }

    @Override
    public Void call() {
        afficheur.update(this.observerDeCapteurAsync);
        return null;
    }
}
