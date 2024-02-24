package com.aoc.servent;

import com.aoc.Capteur;
import com.aoc.proxy.ObserverDeCapteurAsync;

public interface ObserverDeCapteur {
    public void update(ObserverDeCapteurAsync observerDeCapteurAsync);
    public String getName();

    String getValues();
}
