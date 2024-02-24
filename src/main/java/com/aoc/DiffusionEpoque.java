package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.scheduler.SchedulerImpl;

import java.util.List;

/**
 * Implémentation de l'interface AlgoDiffusion représentant une diffusion par époque.
 * Cette classe permet de configurer un Scheduler avec un délai fixe pour l'exécution des mises à jour,
 * mais les méthodes configure() et execute() sont laissées non implémentées car la diffusion par époque n'est pas spécifiée.
 */
public class DiffusionEpoque implements AlgoDiffusion {
    /**
     * Nombre de threads dans le pool d'exécution utilisé pour la diffusion.
     */
    private int nbPool = 1;

    /**
     * Configure l'algorithme de diffusion avec le capteur et les observateurs de capteur asynchrones.
     * Cette méthode n'est pas implémentée car la diffusion par époque n'est pas spécifiée.
     *
     * @param capteur                 Le capteur à associer à l'algorithme de diffusion.
     * @param observerDeCapteurAsyncs La liste des observateurs de capteur asynchrones à associer à l'algorithme de diffusion.
     */
    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs) {
        // Non implémenté
    }

    /**
     * Exécute l'algorithme de diffusion.
     * Cette méthode n'est pas implémentée car la diffusion par époque n'est pas spécifiée.
     */
    @Override
    public void execute() {
        // Non implémenté
    }

    /**
     * Récupère la valeur actuelle du capteur.
     *
     * @return La valeur actuelle du capteur.
     */
    @Override
    public int getValue() {
        return 0;
    }

    /**
     * Récupère le Scheduler associé à l'algorithme de diffusion.
     * Crée un Scheduler avec un délai de 3000 millisecondes pour l'exécution des mises à jour.
     *
     * @return Le Scheduler associé à l'algorithme de diffusion.
     */
    @Override
    public Scheduler getScheduler() {
        Scheduler scheduler = new SchedulerImpl(this.nbPool);
        scheduler.setDelay(3000L);
        return scheduler;
    }

    /**
     * Définit le nombre de threads dans le pool d'exécution utilisé pour la diffusion.
     *
     * @param pool Le nombre de threads dans le pool d'exécution.
     */
    @Override
    public void setNbPool(int pool) {
        this.nbPool = pool;
    }
}

