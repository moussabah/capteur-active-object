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
     * Met à jour l'observateur de capteur de manière asynchrone.
     *
     * @param observerDeCapteurAsync L'objet ObserverDeCapteurAsync à mettre à jour.
     * @return Un Future<Void> représentant l'état de la mise à jour.
     */
    Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * Récupère la valeur du capteur.
     *
     * @return Un objet GetValue contenant la valeur du capteur.
     */
    GetValue getValue();
}

