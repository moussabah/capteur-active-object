package com.aoc;

import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale contenant la méthode main pour exécuter le programme.
 */
public class Main {
    /**
     * Méthode principale du programme.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans ce programme).
     * @throws InterruptedException Si une interruption survient pendant le sommeil.
     */
    public static void main(String[] args) throws InterruptedException {
        // Nombre d'observateurs à créer
        final int nbObservers = 5;

        // Création d'une liste d'observateurs de capteur (Afficheurs)
        List<ObserverDeCapteur> afficheurs = new ArrayList<>();
        for (int i = 0; i < nbObservers; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }

        // Création d'un capteur avec les observateurs de capteur et une diffusion séquentielle
        Capteur c = new CapteurImpl(afficheurs, new DiffusionSequentielle());

        // Appel de la méthode tick() du capteur deux fois
        c.tick();
        c.tick();
    }
}

