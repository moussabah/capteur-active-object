package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        final int nbThreads = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(nbThreads);

        AlgoDiffusion alog = new DiffusionAtomique();
        List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
        for (int i = 0; i < nbThreads; i++) {
            observerDeCapteurAsyncs.add(new Canal(alog, executorService));
        }
        Capteur c = new CapteurImpl(observerDeCapteurAsyncs, alog);
        c.tick();
    }
}
