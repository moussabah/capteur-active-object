package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.List;

/**
 * Strategy interface
 */
public interface AlgoDiffusion {

    /**
     * Use to define contexte
     *
     * @param capteur                   contexte
     * @param observerDeCapteurAsyncs   canals
     */
    void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs);

    /**
     * invoked when captor tick() will invoke
     */
    void execute();

    /**
     * use to know if all futures tasks is terminated
     * @return true if all future is done
     */
    public boolean isTerminated();

    /**
     * attach new canal
     * @param observerDeCapteurAsync canal
     */
    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * detach canal
     * @param observerDeCapteurAsync canal
     * @return true if observerDeCapteurAsync is detached
     */
    boolean detach(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * Make copy of captor value
     * @return copy of captor value that will depend on strategy type
     */
    int getValue();
}
