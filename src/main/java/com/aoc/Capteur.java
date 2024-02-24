package com.aoc;

public interface Capteur {
    public int getValue();
    public void tick();

    void increment();

    void setValue(int i);
}
