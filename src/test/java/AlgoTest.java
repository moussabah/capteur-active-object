import com.aoc.*;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.*;

public class AlgoTest {
    List<ObserverDeCapteur> afficheurs = new ArrayList<>();
    Capteur capteur;
    ScheduledExecutorService scheduledExecutorService;

    @BeforeEach
    public void setUp(){
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        afficheurs.add(new Afficheur("A1"));
        afficheurs.add(new Afficheur("A2"));
        afficheurs.add(new Afficheur("A3"));
    }

    @Test
    @DisplayName("Atomic diffusion")
    void diffusionAtomicTest(){
        AlgoDiffusion atomicDiffusion =  new DiffusionAtomique();
        capteur = new CapteurImpl(afficheurs, atomicDiffusion);
        for (int i = 0; i < 4; i++) {
            capteur.tick();
        }

        ObserverDeCapteur observerDeCapteur = this.getLongestStr(afficheurs);

        // Compare les valeurs des autres afficheurs à la valeur de l'afficheur qui la plus longue chaîne
        for (ObserverDeCapteur afficheur: afficheurs){
            if (!afficheur.getName().equals(observerDeCapteur.getName())){
                assertTrue(observerDeCapteur.getValues().contains(afficheur.getValues()));
            }
        }

    }

    private ObserverDeCapteur getLongestStr(List<ObserverDeCapteur> afficheurs) {
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
        return observerDeCapteur;
    }

    @Test
    @DisplayName("Sequential diffusion")
    void diffusionSequentialTest() throws InterruptedException {
        AlgoDiffusion sequentialAlgo = new DiffusionSequentielle();
        capteur = new CapteurImpl(afficheurs, sequentialAlgo);
        capteur.tick();
        // Patiente pendant que tous les afficheurs lisent la valeur écrite dans la copie
        Thread.sleep(4000L);
        capteur.tick();
        capteur.tick();
        Thread.sleep(4000L);
        capteur.tick();

        while (!sequentialAlgo.isTerminated()){}

        for (ObserverDeCapteur afficheur : afficheurs){
            System.out.printf(afficheur.getName()+": "+afficheur.getValues()+"\n");
        }
        ObserverDeCapteur observerDeCapteur = this.getLongestStr(afficheurs);
        // Compare les valeurs des autres afficheurs à la valeur de l'afficheur qui la plus longue chaîne
        for (ObserverDeCapteur afficheur: afficheurs){
            if (!afficheur.getName().equals(observerDeCapteur.getName())){
                assertTrue(observerDeCapteur.getValues().contains(afficheur.getValues()));
            }
            else break;
        }

    }
}
