package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface Capteur.
 * Cette classe représente un capteur et permet d'interagir avec lui, notamment en incrémentant sa valeur, en la
 * définissant et en récupérant sa valeur actuelle.
 */
public class CapteurImpl implements  Capteur{
    /**
     * Algorithme de diffusion utilisé par le capteur.
     */
    private final AlgoDiffusion algoDiffusion;
    /**
     * Liste des observateurs de capteur asynchrones associés au capteur.
     */
    private final List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
    /**
     * Liste des observateurs de capteur associés au capteur.
     */
    private final List<ObserverDeCapteur> observerDeCapteurs;
    /**
     * Valeur actuelle du capteur.
     */
    private int value = 0;
    /**
     * Construit une instance de CapteurImpl avec la liste des observateurs de capteur et l'algorithme de diffusion spécifiés.
     *
     * @param observerDeCapteurs La liste des observateurs de capteur associés au capteur.
     * @param algoDiffusion      L'algorithme de diffusion à utiliser pour le capteur.
     */
    public CapteurImpl(List<ObserverDeCapteur> observerDeCapteurs, AlgoDiffusion algoDiffusion){
        this.observerDeCapteurs = observerDeCapteurs;
        this.algoDiffusion = algoDiffusion;
        this.initializeCanals();
        this.algoDiffusion.configure(this, this.observerDeCapteurAsyncs);
    }
    /**
     * Initialise les canaux de communication entre le capteur et les observateurs de capteur.
     */
    private void initializeCanals(){
        Scheduler scheduler = algoDiffusion.getScheduler();
        for (ObserverDeCapteur observerDeCapteur : observerDeCapteurs){
            observerDeCapteurAsyncs.add(new Canal(algoDiffusion, observerDeCapteur, scheduler));
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
    public int getValue() {
        return this.value;
    }

    @Override
    public void tick() {
        algoDiffusion.execute();
    }

}
