package com.aoc;

/**
 * Interface représentant un capteur.
 * Les classes implémentant cette interface doivent fournir des méthodes pour obtenir la valeur actuelle du capteur,
 * avancer d'un pas de temps, incrémenter la valeur du capteur et définir une nouvelle valeur pour le capteur.
 */
public interface Capteur {

    /**
     * Récupère la valeur actuelle du capteur.
     *
     * @return La valeur actuelle du capteur.
     */
    int getValue();

    /**
     * Avance le capteur d'un pas de temps.
     */
    void tick();

    /**
     * Incrémente la valeur du capteur.
     */
    void increment();

    /**
     * Définit une nouvelle valeur pour le capteur.
     *
     * @param i La nouvelle valeur à définir pour le capteur.
     */
    void setValue(int i);
}

