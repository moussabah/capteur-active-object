package com.aoc.proxy;

import com.aoc.AlgoDiffusion;
import com.aoc.GetValue;
import com.aoc.invocation.Update;

import java.util.concurrent.*;

public class Canal implements ObserverDeCapteurAsync {

    private AlgoDiffusion algoDiffusion;
    public Canal(AlgoDiffusion algoDiffusion){
        this.algoDiffusion = algoDiffusion;
    }

    ExecutorService scheduler = Executors.newFixedThreadPool(1);
    @Override
    public Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        // Create Method Invocation
        Callable<Void>  update = new Update(observerDeCapteurAsync);
        // Enqueue in scheduler
        return scheduler.submit(update);
    }

    @Override
    public Future<GetValue> getValue() {
        int capteurValue = this.algoDiffusion.getValue();
        GetValue value = new GetValue();
        value.setValue(capteurValue);
        return this.scheduler.submit(() -> value);
    }


}
