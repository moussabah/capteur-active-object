package com.aoc.proxy;
import com.aoc.GetValue;
import java.util.concurrent.Future;

/**
 * Interface représentant un observateur de capteur asynchrone.
 * Les classes implémentant cette interface doivent fournir une méthode pour mettre à jour l'observateur de capteur
 * de manière asynchrone et une méthode pour récupérer la valeur du capteur.
 */
public interface ObserverDeCapteurAsync {
    /**
     * Update canals (or proxies) value
     *
     * @param observerDeCapteurAsync self canal
     * @return future after enqueue task on scheduler (ExecutorService)
     */
    Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * Return captor wrapped value
     *
     * @return wrapped captor value
     */
    GetValue getValue();
}

