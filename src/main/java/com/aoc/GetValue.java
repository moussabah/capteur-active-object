package com.aoc;

/**
 * Classe représentant une valeur captée.
 * Cette classe permet de stocker et d'accéder à une valeur captée par un capteur ou un observateur de capteur.
 */
public class GetValue {
    /**
     * Valeur captée.
     */
    private int value;

    /**
     * Définit la valeur captée.
     *
     * @param value La valeur captée à définir.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Récupère la valeur captée.
     *
     * @return La valeur captée.
     */
    public int getValue() {
        return value;
    }
}

