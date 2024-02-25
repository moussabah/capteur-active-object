package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

public interface Capteur {
    public int getValue();
    public void tick();

    void increment();

    void setValue(int i);

    void attach(ObserverDeCapteurAsync observerDeCapteurAsync);

    void detach(ObserverDeCapteurAsync observerDeCapteur);
}
