package com.aoc.invocation;

import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.Callable;

/**
 * Représente une tâche de mise à jour qui implémente l'interface Callable.
 * Cette classe permet de mettre à jour un ObserverDeCapteurAsync en utilisant un ObserverDeCapteur.
 */
public class Update implements Callable<Void> {

    /**
     * Compteur pour suivre le nombre d'instances créées.
     */
    public static int counter = 0;

    /**
     * Instance d'ObserverDeCapteurAsync.
     */
    private final ObserverDeCapteurAsync observerDeCapteurAsync;

    /**
     * Instance d'ObserverDeCapteur.
     */
    private final ObserverDeCapteur observerDeCapteur;

    /**
     * Construit une instance de Update avec l'ObserverDeCapteurAsync et l'ObserverDeCapteur donnés.
     *
     * @param observerDeCapteurAsync instance d'ObserverDeCapteurAsync à mettre à jour.
     * @param observerDeCapteur      instance d'ObserverDeCapteur utilisée pour la mise à jour.
     */
    public Update(ObserverDeCapteurAsync observerDeCapteurAsync, ObserverDeCapteur observerDeCapteur){
        Update.counter++;
        this.observerDeCapteurAsync = observerDeCapteurAsync;
        this.observerDeCapteur = observerDeCapteur;
    }

    /**
     * Exécute l'opération de mise à jour en appelant la méthode update de ObserverDeCapteur avec ObserverDeCapteurAsync.
     *
     * @return Renvoie toujours null.
     */
    @Override
    public Void call() {
        observerDeCapteur.update(this.observerDeCapteurAsync);
        return null;
    }
}
