package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.scheduler.SchedulerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Implémentation de l'interface AlgoDiffusion représentant une diffusion séquentielle.
 * Cette classe permet de diffuser une mise à jour du capteur à tous les observateurs de manière séquentielle.
 */
public class DiffusionSequentielle implements AlgoDiffusion {

    /**
     * Le capteur associé à l'algorithme de diffusion.
     */
    private Capteur capteur;

    /**
     * Liste des observateurs de capteur asynchrones associés à l'algorithme de diffusion.
     */
    private List<ObserverDeCapteurAsync> proxies;

    /**
     * Valeur mise à jour du capteur.
     */
    private int value;

    /**
     * Liste des résultats de la mise à jour des observateurs.
     */
    private List<Future<Void>> futures = new ArrayList<>();

    /**
     * Nombre de threads dans le pool d'exécution utilisé pour la diffusion.
     */
    private int nbPool = 1;

    /**
     * Configure l'algorithme de diffusion avec le capteur et les observateurs de capteur asynchrones.
     *
     * @param capteur                 Le capteur à associer à l'algorithme de diffusion.
     * @param observerDeCapteurAsyncs La liste des observateurs de capteur asynchrones à associer à l'algorithme de diffusion.
     */
    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> observerDeCapteurAsyncs) {
        this.capteur = capteur;
        this.proxies = observerDeCapteurAsyncs;
    }

    /**
     * Exécute l'algorithme de diffusion séquentielle en incrémentant la valeur du capteur et en mettant à jour tous les observateurs de manière séquentielle.
     */
    @Override
    public void execute() {
        this.capteur.increment();
        boolean isReady = true;
        for (Future<Void> f : futures){
            if (!f.isDone()){
                isReady = false;
                break;
            }
        }

        if (isReady){
            this.futures.clear();
            this.value = this.capteur.getValue();
            this.proxies.forEach(proxy -> {
                this.futures.add(proxy.update(proxy));
            });
        }
    }

    /**
     * Récupère la valeur actuelle du capteur.
     *
     * @return La valeur actuelle du capteur.
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * Récupère le Scheduler associé à l'algorithme de diffusion.
     *
     * @return Le Scheduler associé à l'algorithme de diffusion.
     */
    @Override
    public Scheduler getScheduler() {
        return new SchedulerImpl(this.nbPool);
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

