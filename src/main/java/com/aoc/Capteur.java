package com.aoc;

import com.aoc.servent.ObserverDeCapteur;

public interface Capteur {
    public void attach(ObserverDeCapteur observerCapteur);
    public void detach(ObserverDeCapteur observerCapteur);
    public int getValue();
    public void tick();

    public void lock();

    public void unlock();

    void increment();

    void setValue(int i);
}
