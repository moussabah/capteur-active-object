package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        final int nbThreads = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AlgoDiffusion alog = new DiffusionSequentielle();
        List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
        for (int i = 0; i < nbThreads; i++) {
            ObserverDeCapteur observerDeCapteur = new Afficheur("A"+i);
            observerDeCapteurAsyncs.add(new Canal(alog, observerDeCapteur, executorService));
        }
        Capteur c = new CapteurImpl(observerDeCapteurAsyncs, alog);
        c.tick();
    }
}
