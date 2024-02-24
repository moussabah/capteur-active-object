package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;

import java.util.List;

/**
 * Interface représentant un algorithme de diffusion.
 * Les classes implémentant cette interface doivent fournir des méthodes pour configurer l'algorithme avec un capteur
 * et une liste d'observateurs de capteur asynchrones, exécuter l'algorithme, obtenir la valeur du capteur,
 * obtenir le Scheduler associé à l'algorithme et définir le nombre de threads dans le pool d'exécution.
 */
public interface AlgoDiffusion {

    /**
     * Configure l'algorithme de diffusion avec le capteur et la liste des observateurs de capteur asynchrones.
     *
     * @param capteur                    Le capteur à associer à l'algorithme.
     * @param observerDeCapteurAsyncs   La liste des observateurs de capteur asynchrones à associer à l'algorithme.
     */
    void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs);

    /**
     * Exécute l'algorithme de diffusion.
     */
    void execute();

    /**
     * Récupère la valeur du capteur.
     *
     * @return La valeur actuelle du capteur.
     */
    int getValue();

    /**
     * Récupère le Scheduler associé à l'algorithme de diffusion.
     *
     * @return Le Scheduler associé à l'algorithme.
     */
    Scheduler getScheduler();

    /**
     * Définit le nombre de threads dans le pool d'exécution utilisé par l'algorithme de diffusion.
     *
     * @param pool Le nombre de threads dans le pool d'exécution.
     */
    void setNbPool(int pool);
}
