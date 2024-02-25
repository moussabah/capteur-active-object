package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;

public class CapteurImpl implements  Capteur{
    /**
     * Strategy algorithm
     */
    private final AlgoDiffusion algoDiffusion;
    /**
     * list of observers: canals
     */
    private final List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
    /**
     * captor observers
     */
    private List<ObserverDeCapteur> observerDeCapteurs = new ArrayList<>();

    private int value = 0;
    /**
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

    public CapteurImpl(AlgoDiffusion algoDiffusion){
        this.algoDiffusion = algoDiffusion;
        this.algoDiffusion.configure(this, this.observerDeCapteurAsyncs);
    }

    /**
     * Initialize canals with afficheurs
     */
    private void initializeCanals(){
        for (ObserverDeCapteur observerDeCapteur : observerDeCapteurs){
            observerDeCapteurAsyncs.add(new Canal(algoDiffusion, observerDeCapteur));
        }
    }

    /**
     * user for update captor value from strategy
     */
    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public void setValue(int i) {
        this.value = i;
    }

    /**
     * Attach proxy that is ObserverDeCapteurAsync
     * @param observerDeCapteurAsync
     */
    @Override
    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync) {
        this.algoDiffusion.attach(observerDeCapteurAsync);
    }

    /**
     * Detach proxy
     * @param observerDeCapteur
     */
    @Override
    public void detach(ObserverDeCapteurAsync observerDeCapteur) {
        this.algoDiffusion.detach(observerDeCapteur);
    }


    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * execute strategry when tick in invoke
     */
    @Override
    public void tick() {
        algoDiffusion.execute();
    }

}
