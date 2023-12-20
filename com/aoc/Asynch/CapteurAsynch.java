package com.aoc.Asynch;

import com.aoc.ObserverDeCapteur;

import java.util.concurrent.Future;

public interface CapteurAsynch {

    public void attach(ObserverDeCapteur observerCapteur);
    public void detach(ObserverDeCapteur observerCapteur);
    public Future<Integer> getValue();
    public void tick();
    public void lock();
    public void unlock();
}
