package com.aoc.servent;

import com.aoc.GetValue;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class Afficheur implements ObserverDeCapteur {

    private final String name;

    private int currentValue;

    List<Integer> values = new ArrayList<>();

    public Afficheur(String name) {
        this.name = name;
    }

    @Override
    public void update(ObserverDeCapteurAsync observerDeCapteurAsync) {
        GetValue valueAsync = observerDeCapteurAsync.getValue();
        this.currentValue = valueAsync.getValue();
        System.out.printf("%s ("+Thread.currentThread().getName()+") = %d\n", this.name, valueAsync.getValue());
        values.add(valueAsync.getValue());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValues() {
        StringBuilder valuesToString = new StringBuilder();
        for (int i = 0; i < values.size(); i++){
            valuesToString.append(values.get(i).toString());
            if (i != values.size()-1 ){
                valuesToString.append(",");
            }
        }
        return valuesToString.toString();
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
