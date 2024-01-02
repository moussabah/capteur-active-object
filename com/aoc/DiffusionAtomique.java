package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    private List<ObserverDeCapteurAsync> proxies;
    private GetValue capteurValue = new GetValue();
    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> proxies) {
        this.capteur = capteur;
        this.proxies = proxies;
    }

    @Override
    public void execute() {
        this.capteur.lock();
        this.capteurValue.setValue(this.capteur.getValue());
        this.proxies.forEach(proxy -> {
            proxy.update(proxy);
        });
        //this.capteur.unlock();
    }

    @Override
    public int getValue() {
        return this.capteur.getValue();
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
}
