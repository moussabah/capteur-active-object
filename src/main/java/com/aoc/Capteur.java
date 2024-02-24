package com.aoc;

import com.aoc.servent.ObserverDeCapteur;

public interface Capteur {
    public int getValue();
    public void tick();

    void increment();
}
