package com.aoc.servent;

import com.aoc.proxy.ObserverDeCapteurAsync;

/**
 * Interface représentant un observateur de capteur.
 * Les classes implémentant cette interface doivent fournir des méthodes pour mettre à jour l'observateur avec
 * une valeur captée, obtenir le nom de l'observateur, et obtenir les valeurs captées stockées par l'observateur.
 */
public interface ObserverDeCapteur {

    /**
     * Met à jour l'observateur avec la valeur captée par l'observateur de capteur asynchrone.
     *
     * @param observerDeCapteurAsync L'observateur de capteur asynchrone dont la valeur est captée.
     */
    void update(ObserverDeCapteurAsync observerDeCapteurAsync);

    /**
     * Retourne le nom de l'observateur de capteur.
     *
     * @return Le nom de l'observateur de capteur.
     */
    String getName();

    /**
     * Retourne les valeurs captées et stockées par l'observateur de capteur sous forme de chaîne de caractères.
     *
     * @return Les valeurs captées et stockées par l'observateur de capteur.
     */
    String getValues();
}

