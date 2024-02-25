package com.aoc;

import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        final int nbObservers = 5;

        List<ObserverDeCapteur> afficheurs = new ArrayList<>();
        for (int i = 0; i < nbObservers; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }
        Capteur c = new CapteurImpl(afficheurs, new DiffusionAtomique());

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //periodic call each second
        scheduledExecutorService.scheduleAtFixedRate(c::tick, 0, 1L, TimeUnit.SECONDS);
        //stop periodic call after 10 seconds
        scheduledExecutorService.schedule(scheduledExecutorService::shutdown, 10L, TimeUnit.SECONDS);
    }
}

