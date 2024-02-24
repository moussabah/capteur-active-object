import com.aoc.*;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.scheduler.Scheduler;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.AccountLockedException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgoTest {
    List<ObserverDeCapteur> afficheurs = new ArrayList<>();
    Capteur capteur;


    @BeforeEach
    public void setUp(){
        afficheurs.add(new Afficheur("A1"));
        afficheurs.add(new Afficheur("A2"));
        afficheurs.add(new Afficheur("A3"));
    }

    @Test
    void diffusionAtomicTest(){
        AlgoDiffusion atomicDiffusion =  new DiffusionAtomique();
        Scheduler scheduler = atomicDiffusion.getScheduler();
        capteur = new CapteurImpl(afficheurs, atomicDiffusion);
        capteur.tick();
        capteur.tick();
        capteur.tick();
        int defaultSize  = 0;

        ObserverDeCapteur observerDeCapteur = afficheurs.get(0);
        // Récupère l'afficheur ayant reçu la plus longue chaîne
        for (ObserverDeCapteur afficheur : afficheurs){
            String values = afficheur.getValues();
            if (values.length() > defaultSize){
                defaultSize = values.length();
                observerDeCapteur = afficheur;
            }
        }

        // Compare les valeurs des autres afficheurs à la valeur de l'afficheur qui la plus longue chaîne
        for (ObserverDeCapteur afficheur: afficheurs){
            if (!afficheur.getName().equals(observerDeCapteur.getName())){
                assertTrue(observerDeCapteur.getValues().contains(afficheur.getValues()));
            }
        }

    }

    @Test
    void diffusionSequentialTest(){
        AlgoDiffusion sequentialAlgo = new DiffusionSequentielle();
        capteur = new CapteurImpl(afficheurs, sequentialAlgo);
        capteur.tick();
        capteur.tick();
        capteur.tick();
        capteur.tick();
        while (!sequentialAlgo.isTerminated()){}
    }
}
