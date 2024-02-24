package com.aoc;

import java.util.concurrent.Future;

public interface ObserverAsync {
    public Future upadate(Capteur capteur);
}
