package com.aoc.servent;

import com.aoc.GetValue;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Afficheur implements ObserverDeCapteur {

    private final String name;

    public Afficheur(String name) {
        this.name = name;
    }

    @Override
    public void update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        Future<GetValue> valueAsync = observerDeCapteurAsync.getValue();
        while (!valueAsync.isDone()) {}
        try {
            System.out.printf("%s = %d\n", this.name, valueAsync.get().getValue());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
