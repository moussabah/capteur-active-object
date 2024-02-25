package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.List;
import java.util.concurrent.Future;

public interface AlgoDiffusion {
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs);
    public void execute();

    public int getValue();

    public boolean isTerminated();

    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync);

    boolean detach(ObserverDeCapteurAsync observerDeCapteurAsync);
}
