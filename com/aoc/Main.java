package com.aoc;

import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        final int nbThreads = 5;

        AlgoDiffusion alog = new DiffusionSequentielle();
        List<ObserverDeCapteur> afficheurs = new ArrayList<>();
        for (int i = 0; i < nbThreads; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }
        Capteur c = new CapteurImpl(afficheurs, alog);
        c.tick();
        c.tick();

    }
}
