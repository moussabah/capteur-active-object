package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;

public class CapteurImpl implements  Capteur{
    private final AlgoDiffusion algoDiffusion;

    private final List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
    private List<ObserverDeCapteur> observerDeCapteurs = new ArrayList<>();

    private int value = 0;

    public CapteurImpl(List<ObserverDeCapteur> observerDeCapteurs, AlgoDiffusion algoDiffusion){
        this.observerDeCapteurs = observerDeCapteurs;
        this.algoDiffusion = algoDiffusion;
        this.initializeCanals();
        this.algoDiffusion.configure(this, this.observerDeCapteurAsyncs);
    }

    public CapteurImpl(AlgoDiffusion algoDiffusion){
        this.algoDiffusion = algoDiffusion;
        this.algoDiffusion.configure(this, this.observerDeCapteurAsyncs);
    }

    private void initializeCanals(){
        for (ObserverDeCapteur observerDeCapteur : observerDeCapteurs){
            observerDeCapteurAsyncs.add(new Canal(algoDiffusion, observerDeCapteur));
        }
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
    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync) {
        this.algoDiffusion.attach(observerDeCapteurAsync);
    }

    @Override
    public void detach(ObserverDeCapteurAsync observerDeCapteur) {
        this.algoDiffusion.detach(observerDeCapteur);
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
