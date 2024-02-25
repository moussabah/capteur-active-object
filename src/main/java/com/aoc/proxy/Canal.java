package com.aoc.proxy;

import com.aoc.AlgoDiffusion;
import com.aoc.GetValue;
import com.aoc.invocation.Update;
import com.aoc.servent.ObserverDeCapteur;

import java.util.concurrent.*;

public class Canal implements ObserverDeCapteurAsync {

    private final AlgoDiffusion algoDiffusion;
    private final ObserverDeCapteur observerDeCapteur;

    ExecutorService executorService = Executors.newScheduledThreadPool(1);
    public Canal(AlgoDiffusion algoDiffusion, ObserverDeCapteur observerDeCapteur){
        this.algoDiffusion = algoDiffusion;
        this.observerDeCapteur = observerDeCapteur;
    }
    @Override
    public Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        // Create Method Invocation
        Callable<Void>  update = new Update(this, observerDeCapteur);
        return executorService.submit(update);
    }

    @Override
    public GetValue getValue() {
        int capteurValue = this.algoDiffusion.getValue();
        GetValue value = new GetValue();
        value.setValue(capteurValue);
        return value;
    }


}
