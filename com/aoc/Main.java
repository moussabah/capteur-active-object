package com.aoc;

import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        final int nbObservers = 5;

        List<ObserverDeCapteur> afficheurs = new ArrayList<>();
        for (int i = 0; i < nbObservers; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }
        Capteur c = new CapteurImpl(afficheurs, new DiffusionSequentielle());
        c.tick();
        c.tick();

    }
}
