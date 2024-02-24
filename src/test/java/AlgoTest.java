import com.aoc.*;
import com.aoc.scheduler.Scheduler;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        capteur = new CapteurImpl(afficheurs, atomicDiffusion);
        capteur.tick();
        capteur.tick();
        capteur.tick();

        int defaultSize  = 0;

        ObserverDeCapteur observerDeCapteur = afficheurs.get(0);
        for (ObserverDeCapteur afficheur : afficheurs){
            String values = afficheur.getValues();
            if (values.length() > defaultSize){
                defaultSize = values.length();
                observerDeCapteur = afficheur;
            }
        }

        for (ObserverDeCapteur afficheur: afficheurs){
            if (!afficheur.getName().equals(observerDeCapteur.getName())){
                System.out.println("Test...");
                assertTrue(observerDeCapteur.getValues().contains(afficheur.getValues()));
            }
            else break;
        }

    }

    @Test
    void diffusionSequentialTest(){
        capteur = new CapteurImpl(afficheurs, new DiffusionSequentielle());
    }
}
