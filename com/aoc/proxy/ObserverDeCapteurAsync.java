package com.aoc.proxy;

import com.aoc.Capteur;
import com.aoc.GetValue;

import java.util.concurrent.Future;

public interface ObserverDeCapteurAsync {
    Future<Void> update(ObserverDeCapteurAsync observerDeCapteurAsync);

    public Future<GetValue> getValue();
}
