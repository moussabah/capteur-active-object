package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.List;

public class CapteurImpl implements  Capteur{
    private AlgoDiffusion algoDiffusion;

    private List<ObserverDeCapteurAsync> observerDeCapteurAsyncs;

    public CapteurImpl(List<ObserverDeCapteurAsync> observerDeCapteurAsyncs, AlgoDiffusion algoDiffusion){
        this.observerDeCapteurAsyncs = observerDeCapteurAsyncs;
        this.algoDiffusion = algoDiffusion;
        this.algoDiffusion.configure(this);
    }

    private int value = 0;

    @Override
    public void attach(ObserverDeCapteur observerCapteur) {

    }

    @Override
    public void detach(ObserverDeCapteur observerCapteur) {

    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void tick() {
        this.value++;
    }
}
