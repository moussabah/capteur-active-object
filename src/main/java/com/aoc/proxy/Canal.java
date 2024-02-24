package com.aoc.proxy;

import com.aoc.AlgoDiffusion;
import com.aoc.GetValue;
import com.aoc.invocation.Update;
import com.aoc.scheduler.Scheduler;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.*;

/**
 * Représente un canal qui implémente l'interface ObserverDeCapteurAsync.
 * Cette classe permet de mettre à jour un ObserverDeCapteurAsync et de récupérer une valeur à partir d'un AlgoDiffusion.
 */
public class Canal implements ObserverDeCapteurAsync {

    /**
     * Algorithme de diffusion utilisé par le canal.
     */
    private final AlgoDiffusion algoDiffusion;

    /**
     * Planificateur utilisé par le canal pour l'ordonnancement.
     */
    private final Scheduler scheduler;

    /**
     * Observateur de capteur associé au canal.
     */
    private final ObserverDeCapteur observerDeCapteur;

    /**
     * Construit une instance de Canal avec l'AlgoDiffusion, l'ObserverDeCapteur et le Scheduler donnés.
     *
     * @param algoDiffusion    L'algorithme de diffusion à utiliser.
     * @param observerDeCapteur L'observateur de capteur à associer au canal.
     * @param scheduler        Le planificateur à utiliser pour l'ordonnancement.
     */
    public Canal(AlgoDiffusion algoDiffusion, ObserverDeCapteur observerDeCapteur, Scheduler scheduler){
        this.scheduler = scheduler;
        this.algoDiffusion = algoDiffusion;
        this.observerDeCapteur = observerDeCapteur;
    }

    /**
     * Met à jour l'ObserverDeCapteurAsync en créant une tâche d'update et en l'ordonnant avec le Scheduler.
     *
     * @param observerDeCapteurAsync L'ObserverDeCapteurAsync à mettre à jour.
     * @return Un Future<Void> représentant l'état de la mise à jour.
     */
    @Override
    public Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        // Crée l'invocation de méthode
        Callable<Void>  update = new Update(this, observerDeCapteur);
        return scheduler.enqueue(update);
    }

    /**
     * Récupère la valeur du capteur à partir de l'AlgoDiffusion.
     *
     * @return Un objet GetValue contenant la valeur du capteur.
     */
    @Override
    public GetValue getValue() {
        int capteurValue = this.algoDiffusion.getValue();
        GetValue value = new GetValue();
        value.setValue(capteurValue);
        return value;
    }
}
