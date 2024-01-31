package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    int counter = 0;
    private List<ObserverDeCapteurAsync> proxies;

    private List<Future<Void>> results = new ArrayList<>();
    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> proxies) {
        this.capteur = capteur;
        this.proxies = proxies;
    }

    @Override
    public void execute() {
        this.capteur.increment();
        this.proxies.forEach(proxy -> {
            this.results.add(proxy.update(proxy));
        });
        for (Future<Void> res : results){
            while (!res.isDone()){}
        }
        this.results.clear();
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
