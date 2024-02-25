package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.List;

/**
 * Epoque diffusion strategy
 */
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

    @Override
    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync) {

    }

    @Override
    public boolean detach(ObserverDeCapteurAsync observerDeCapteurAsync) {
        return false;
    }
}

