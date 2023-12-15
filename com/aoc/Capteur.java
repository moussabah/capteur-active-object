package com.aoc;

public interface Capteur {
    public void attach(ObserverDeCapteur observerCapteur);
    public void detach(ObserverDeCapteur observerCapteur);
    public int getValue();
    public void tick();
}
