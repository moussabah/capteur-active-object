package com.aoc.proxy;

import com.aoc.AlgoDiffusion;
import com.aoc.GetValue;
import com.aoc.invocation.Update;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class Canal implements ObserverDeCapteurAsync {

    private final AlgoDiffusion algoDiffusion;
    private final ExecutorService scheduler;
    public Canal(AlgoDiffusion algoDiffusion, ExecutorService executorService){
        this.scheduler = executorService;
        this.algoDiffusion = algoDiffusion;
    }
    @Override
    public Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        // Create Method Invocation
        Callable<Void>  update = new Update(this);
        return scheduler.submit(update);
    }

    @Override
    public GetValue getValue() {
        int capteurValue = this.algoDiffusion.getValue();
        GetValue value = new GetValue();
        value.setValue(capteurValue);
        return value;
    }


}
