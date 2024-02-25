package com.aoc;


import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

/**
 * Captor interface
 */
public interface Capteur {
    /**
     * Get captor value
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

    /**
     * set captor value
     * @param i captor value
     */
    void setValue(int i);

    /**
     * attach new proxy
     * @param observerDeCapteurAsync canal
     */
    void attach(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * Detach proxy
     * @param observerDeCapteur canal
     */
    void detach(ObserverDeCapteurAsync observerDeCapteur);
}

