package com.aoc.proxy;

import com.aoc.Capteur;

import java.util.concurrent.Future;

public interface ObserverDeCapteurAsync {
    Future<Void> update(Capteur capteur);
}
