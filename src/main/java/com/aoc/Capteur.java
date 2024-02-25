package com.aoc;


import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

/**
 * Captor interface
 */
public interface Capteur {
    /**
     * @return capteur current value
     */
    public int getValue();

    /**
     * Invoke from client, and with trigger strategy execute
     */
    public void tick();

    /**
     * Update captor value by increment
     */
    void increment();

    void setValue(int i);

    /**
     * attach new proxy
     * @param observerDeCapteurAsync
     */
    void attach(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * Detach proxy
     * @param observerDeCapteur
     */
    void detach(ObserverDeCapteurAsync observerDeCapteur);
}

