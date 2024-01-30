package com.aoc.servent;

import com.aoc.GetValue;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class Afficheur implements ObserverDeCapteur {

    private final String name;

    public Afficheur(String name) {
        this.name = name;
    }

    @Override
    public void update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        GetValue valueAsync = observerDeCapteurAsync.getValue();
        System.out.printf("%s ("+Thread.currentThread().getName()+") = %d\n", this.name, valueAsync.getValue());
    }

    @Override
    public String getName() {
        return this.name;
    }
}
