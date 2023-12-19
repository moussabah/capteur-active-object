package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.List;

public interface AlgoDiffusion {
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs);
    public void execute();
}
