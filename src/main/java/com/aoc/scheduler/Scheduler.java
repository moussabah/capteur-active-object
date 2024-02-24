package com.aoc.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Interface représentant le Scheduler.
 * Les classes implémentant cette interface doivent fournir des méthodes pour planifier l'exécution de tâches,
 * définir un délai et vérifier si le Scheduler est terminé.
 */
public interface Scheduler {

    /**
     * Ajoute une tâche à la file d'attente pour être exécutée dans le futur.
     *
     * @param callable La tâche à ajouter à la file d'attente.
     * @return Future<Void> représentant l'état de l'exécution de la tâche.
     */
    Future<Void> enqueue(Callable<Void> callable);

    /**
     * Définit le délai pour l'exécution des tâches dans le Scheduler.
     *
     * @param delay Le délai en millisecondes pour l'exécution des tâches.
     */
    void setDelay(long delay);

    /**
     * Vérifie si le Scheduler est terminé.
     *
     * @return true si le Scheduler est terminé, sinon false.
     */
    boolean isTerminated();
}
