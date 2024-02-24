package com.aoc;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.scheduler.SchedulerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Implémentation de l'interface AlgoDiffusion représentant une diffusion atomique.
 * Cette classe permet de diffuser une mise à jour du capteur à tous les observateurs de manière atomique.
 */
public class DiffusionAtomique implements AlgoDiffusion {

    /**
     * Le capteur associé à l'algorithme de diffusion.
     */
    private Capteur capteur;

    /**
     * Liste des observateurs de capteur asynchrones associés à l'algorithme de diffusion.
     */
    private List<ObserverDeCapteurAsync> proxies;

    /**
     * Liste des résultats de la mise à jour des observateurs.
     */
    private List<Future<Void>> results = new ArrayList<>();

    /**
     * Nombre de threads dans le pool d'exécution utilisé pour la diffusion.
     */
    private int nbPool = 1;

    /**
     * Configure l'algorithme de diffusion avec le capteur et les observateurs de capteur asynchrones.
     *
     * @param capteur Le capteur à associer à l'algorithme de diffusion.
     * @param proxies La liste des observateurs de capteur asynchrones à associer à l'algorithme de diffusion.
     */
    @Override
    public void configure(Capteur capteur, List<ObserverDeCapteurAsync> proxies) {
        this.capteur = capteur;
        this.proxies = proxies;
    }

    /**
     * Exécute l'algorithme de diffusion atomique en incrémentant la valeur du capteur et en mettant à jour tous les observateurs.
     */
    @Override
    public void execute() {
        this.capteur.increment();
        this.proxies.forEach(proxy -> {
            this.results.add(proxy.update(proxy));
        });
        for (Future<Void> res : results){
            while (!res.isDone()){}
        }
        this.results.clear();
    }

    /**
     * Récupère la valeur actuelle du capteur.
     *
     * @return La valeur actuelle du capteur.
     */
    @Override
    public int getValue() {
        return this.capteur.getValue();
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

