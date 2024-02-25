package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.List;

public class DiffusionEpoque implements AlgoDiffusion{

    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs) {

    }

    @Override
    public void execute() {

    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
