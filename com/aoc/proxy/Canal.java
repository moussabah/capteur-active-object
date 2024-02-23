package com.aoc.proxy;

import com.aoc.AlgoDiffusion;
import com.aoc.GetValue;
import com.aoc.invocation.Update;
import com.aoc.scheduler.Scheduler;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.*;

public class Canal implements ObserverDeCapteurAsync {

    private final AlgoDiffusion algoDiffusion;
    private final Scheduler scheduler;
    private final ObserverDeCapteur observerDeCapteur;
    public Canal(AlgoDiffusion algoDiffusion, ObserverDeCapteur observerDeCapteur, Scheduler scheduler){
        this.scheduler = scheduler;
        this.algoDiffusion = algoDiffusion;
        this.observerDeCapteur = observerDeCapteur;
    }
    @Override
    public Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        // Create Method Invocation
        Callable<Void>  update = new Update(this, observerDeCapteur);
        return scheduler.enqueue(update);
    }

    @Override
    public GetValue getValue() {
        int capteurValue = this.algoDiffusion.getValue();
        GetValue value = new GetValue();
        value.setValue(capteurValue);
        return value;
    }


}
