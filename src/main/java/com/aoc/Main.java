package com.aoc;

import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {


        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre d'afficheurs: ");
        int nbObservers = sc.nextInt();

        System.out.print("Type de diffuseur: [1. Atomique, 2. Sequentiel]: ");

        int type = sc.nextInt();


        List<ObserverDeCapteur> afficheurs = new ArrayList<>();
        for (int i = 0; i < nbObservers; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }
        AlgoDiffusion algoDiffusion = switch (type) {
            case 1 -> new DiffusionAtomique();
            case 2 -> new DiffusionSequentielle();
            default -> throw new Exception("Unknown value entered !");
        };

        Capteur c = new CapteurImpl(afficheurs, algoDiffusion);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //periodic call each second
        scheduledExecutorService.scheduleAtFixedRate(c::tick, 0, 1L, TimeUnit.SECONDS);
    }
}

