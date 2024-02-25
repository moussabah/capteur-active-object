package com.aoc.proxy;

import com.aoc.AlgoDiffusion;
import com.aoc.GetValue;
import com.aoc.invocation.Update;
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
     * Observateur de capteur associé au canal.
     */
    private final ObserverDeCapteur observerDeCapteur;

    ExecutorService executorService = Executors.newScheduledThreadPool(1);
    /**
     * Canal constructor
     *
     * @param algoDiffusion    strategy diffusion
     * @param observerDeCapteur observer that canal interacts with
     */
    public Canal(AlgoDiffusion algoDiffusion, ObserverDeCapteur observerDeCapteur){
        this.algoDiffusion = algoDiffusion;
        this.observerDeCapteur = observerDeCapteur;
    }

    /**
     * Update each observer by enqueue Update class Method Invocation
     *
     * @param observerDeCapteurAsync canal
     * @return future return by scheduler
     */
    @Override
    public Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        // Create Method Invocation
        Callable<Void>  update = new Update(this, observerDeCapteur);
        return executorService.submit(update);
    }

    /**
     * @see AlgoDiffusion
     */
    @Override
    public GetValue getValue() {
        int capteurValue = this.algoDiffusion.getValue();
        GetValue value = new GetValue();
        value.setValue(capteurValue);
        return value;
    }
}
