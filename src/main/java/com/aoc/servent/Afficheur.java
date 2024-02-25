package com.aoc.servent;

import com.aoc.GetValue;
import com.aoc.proxy.ObserverDeCapteurAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un afficheur qui implémente l'interface ObserverDeCapteur.
 * Cette classe permet d'afficher les valeurs captées par un capteur et de les stocker.
 */
public class Afficheur implements ObserverDeCapteur {

    /**
     * Nom de l'afficheur.
     */
    private final String name;

    private int currentValue;

    /**
     * Liste des valeurs captées et stockées par l'afficheur.
     */
    List<Integer> values = new ArrayList<>();

    /**
     * Construit une instance de Afficheur avec le nom spécifié.
     *
     * @param name Le nom de l'afficheur.
     */
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
            valuesToString.append(values.get(i));
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
