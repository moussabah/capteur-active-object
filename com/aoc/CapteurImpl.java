package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

public class CapteurImpl implements  Capteur{
    private final AlgoDiffusion algoDiffusion;

    private final List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
    private final List<ObserverDeCapteur> observerDeCapteurs;

    private int value = 0;

    private boolean lock = false;

    public CapteurImpl(List<ObserverDeCapteur> observerDeCapteurs, AlgoDiffusion algoDiffusion){
        this.observerDeCapteurs = observerDeCapteurs;
        this.algoDiffusion = algoDiffusion;
        this.initializeCanals();
        this.algoDiffusion.configure(this, this.observerDeCapteurAsyncs);
    }

    private void initializeCanals(){
        Scheduler scheduler = algoDiffusion.getScheduler();
        for (ObserverDeCapteur observerDeCapteur : observerDeCapteurs){
            observerDeCapteurAsyncs.add(new Canal(algoDiffusion, observerDeCapteur, scheduler));
        }
    }

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
