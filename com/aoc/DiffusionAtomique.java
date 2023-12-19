package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.List;

public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    private List<ObserverDeCapteurAsync> observerDeCapteurAsyncs;

    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs) {
        this.capteur = capteur;
        this.observerDeCapteurAsyncs = observerDeCapteurAsyncs;
    }

    @Override
    public void execute() {
        this.capteur.lock();
        this.observerDeCapteurAsyncs.forEach(observer -> {
            observer.update(this.capteur);
        });
        this.capteur.unlock();
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
}
