package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.List;
import java.util.logging.Logger;

public class CapteurImpl implements  Capteur{
    private AlgoDiffusion algoDiffusion;

    private List<ObserverDeCapteurAsync> observerDeCapteurAsyncs;

    public CapteurImpl(List<ObserverDeCapteurAsync> observerDeCapteurAsyncs, AlgoDiffusion algoDiffusion){
        this.observerDeCapteurAsyncs = observerDeCapteurAsyncs;
        this.algoDiffusion = algoDiffusion;
        this.algoDiffusion.configure(this, this.observerDeCapteurAsyncs);
    }

    private int value = 0;

    private boolean lock = false;

    @Override
    public void lock(){
        this.lock = true;
    }

    @Override
    public void unlock(){
        this.lock = false;
    }

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public void setValue(int i) {
        this.value = i;
    }

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
        algoDiffusion.execute();
    }

}
