package com.aoc;

import com.aoc.ObserverCapteur;

public interface Capteur {
    public void attach(ObserverCapteur observerCapteur);
    public void detach(ObserverCapteur observerCapteur);
    public int getValue();
    public void tick();
}
