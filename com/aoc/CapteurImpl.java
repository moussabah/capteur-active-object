package com.aoc;

import com.aoc.servent.ObserverDeCapteur;

public class CapteurImpl implements  Capteur{
    private AlgoDiffusion algoDiffusion;

    public CapteurImpl(AlgoDiffusion algoDiffusion){
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
