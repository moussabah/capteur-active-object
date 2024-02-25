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
     *
     * @return true if all future is done
     */
    public boolean isTerminated();

    /**
     * attach new canal
     * @param observerDeCapteurAsync
     */
    public void attach(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * detach canal
     * @param observerDeCapteurAsync
     * @return true if observerDeCapteurAsync is detached
     */
    boolean detach(ObserverDeCapteurAsync observerDeCapteurAsync);
}
