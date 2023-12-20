package com.aoc.invocation;

import com.aoc.Capteur;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {


    // Create Servant
    private ObserverDeCapteur afficheur = new Afficheur();
    private Capteur  capteur;


    // proxy or Client on param ?
    public Update(Capteur capteur){
        this.capteur = capteur;
    }

    @Override
    public Void call() throws Exception {
        this.capteur.getValue();
        return null;
    }
}
