package com.aoc;

import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlgoDiffusion algo = new DiffusionAtomique();
        List<ObserverDeCapteurAsync> observerDeCapteurAsyncs = new ArrayList<>();       //Ce sont les canaux qui
                                                                                    // obserevent le capteur ?
        for(int i=0; i<5; i++){
            observerDeCapteurAsyncs.add(new Canal());
        }
        Capteur c = new CapteurImpl(observerDeCapteurAsyncs, algo);
        c.tick();
    }
}
