package com.aoc.scheduler;

import java.util.concurrent.*;

/**
 * Implémentation de l'interface Scheduler.
 * Cette classe permet de planifier l'exécution de tâches avec un délai spécifié.
 */
public class SchedulerImpl implements Scheduler {

    /**
     * Service d'exécution planifiée utilisé pour exécuter les tâches.
     */
    ScheduledExecutorService executorService;

    /**
     * Délai par défaut pour l'exécution des tâches.
     */
    private long delay = 0L;

    /**
     * Construit une instance de SchedulerImpl avec le nombre de threads spécifié pour le pool d'execution.
     *
     * @param nbPool Le nombre de threads dans le pool d'exécution.
     */
    public SchedulerImpl(int nbPool){
        this.executorService = Executors.newScheduledThreadPool(nbPool);
    }

    /**
     * Ajoute une tâche à la file d'attente pour être exécutée dans le futur avec le délai spécifié.
     *
     * @param callable La tâche à ajouter à la file d'attente.
     * @return Un Future<Void> représentant l'état de l'exécution de la tâche.
     */
    @Override
    public Future<Void> enqueue(Callable<Void> callable) {
        return this.executorService.schedule(callable, this.delay, TimeUnit.SECONDS);
    }

    /**
     * Définit le délai pour l'exécution des tâches dans le Scheduler.
     *
     * @param delay Le délai en secondes pour l'exécution des tâches.
     */
    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }

    /**
     * Vérifie si le Scheduler est terminé.
     *
     * @return true si le Scheduler est terminé, sinon false.
     */
    @Override
    public boolean isTerminated() {
        return this.executorService.isTerminated();
    }
}

