package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.List;

public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;
    private List<ObserverDeCapteurAsync> proxies;

    /**
     *
     * @param capteur
     * @param observerDeCapteurAsyncs
     */
    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> proxies) {
        this.capteur = capteur;
        this.proxies = proxies;
    }

    @Override
    public void execute() {
        this.capteur.lock();
        this.proxies.forEach(proxy -> {
            proxy.update(this.capteur);
        });
        this.capteur.unlock();
    }

    /**
     * @return Capteur
     */
    public Capteur getCapteur() {
        return this.capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
}
