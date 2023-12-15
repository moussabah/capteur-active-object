package com.aoc.proxy;

import com.aoc.Capteur;
import com.aoc.invocation.Update;

import java.util.concurrent.*;

public class Canal implements ObserverDeCapteurAsync {

    ExecutorService scheduler = Executors.newFixedThreadPool(1);
    @Override
    public Future<Void> update(Capteur capteur) {
        // Create Method Invocation
        Callable<Void>  update = new Update(capteur);
        // Enqueue in scheduler
        return scheduler.submit(update);
    }
}
