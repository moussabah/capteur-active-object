package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlgoDiffusion alog = new DiffusionAtomique();
        List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();
        observerDeCapteurAsyncs.add(new Canal());
        observerDeCapteurAsyncs.add(new Canal());
        observerDeCapteurAsyncs.add(new Canal());
        observerDeCapteurAsyncs.add(new Canal());
        Capteur c = new CapteurImpl(observerDeCapteurAsyncs, alog);
        c.tick();
    }
}
