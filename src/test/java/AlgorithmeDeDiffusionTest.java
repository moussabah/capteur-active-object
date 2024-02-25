import com.aoc.*;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmeDeDiffusionTest{

    private AlgoDiffusion diffusionAto, diffusionEpoque, diffusionSeq;
    private Capteur capteur;
    private List<ObserverDeCapteur> afficheurs;

    @BeforeEach
    void SetUp() {
        this.diffusionAto = new DiffusionAtomique();
        this.diffusionEpoque = new DiffusionEpoque();
        this.diffusionSeq = new DiffusionSequentielle();

        this.afficheurs = new ArrayList<>();
        afficheurs.add(new Afficheur("A1"));
        afficheurs.add(new Afficheur("A2"));
    }

    @AfterEach
    void SetUpDisplayObservers() {
        for (ObserverDeCapteur afficheur : this.afficheurs) {
            System.out.println("" + afficheur.getName() + ": " + afficheur.getValues());
        }
    }

    /*#****************************************  DIFFUSION ATOMIQUE  *************************************/
    @Test
    void initiate_testAtomicConsistency(){
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
        for (ObserverDeCapteur afficheur : afficheurs){         //On s'assure que tous les afficheurs
            assertEquals("1", afficheur.getValues());  // voient la même valeur initial quand on fait le tick
        }
    }

    @Test
    void successCase_AtomicConsistency() {
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
        for(int i = 0; i<2; i++) {
            capteur.tick();
        }
        // Validate atomic consistency
        for (ObserverDeCapteur afficheur : this.afficheurs) {
            assertEquals("1,2,3", afficheur.getValues());
        }
        assertEquals(3, capteur.getValue());
        assertTrue(isAtomicConsistent(afficheurs));
    }

    @Test
    void fail_WithSetCapteurValues_AtomicConsistency() {
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
        for (ObserverDeCapteur afficheur : this.afficheurs) {
            assertEquals("1", afficheur.getValues());   //Valeur initial (premier tick)
        }
        capteur.setValue(2);      // On modifie la valeur actuelle du capteur
        capteur.tick();        // Cette modif sera prise en compte au prochain tick qui va incrémenter la valeur du capteur
        assertEquals(3, capteur.getValue());
        for (ObserverDeCapteur afficheur : this.afficheurs) {
            assertEquals("1,3", afficheur.getValues());
        }
        assertFalse(isAtomicConsistent(afficheurs)); // A1 = 1, 3
    }

    @Test
    void fail_IncrementingValues_AtomicConsistency() {
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
        assertEquals(1, capteur.getValue());
        // Set up scenario
        capteur.setValue(2);
        for(int i = 0; i<4; i++) {
            capteur.tick();
        }
        // Validate atomic consistency
        assertFalse(isAtomicConsistent(afficheurs)); // A1 = A2 = 1,3,4,5,6
    }

    private boolean isAtomicConsistent(List<ObserverDeCapteur> afficheurs) {
        ; // Start with the expected value of 1
        ObserverDeCapteur previous = afficheurs.get(0);
        for (ObserverDeCapteur afficheur : afficheurs) {
            int expectedValue = 1;
            if(! afficheur.getValues().contains(previous.getValues())){
                return false;
            }
            String[] valuesStr = afficheur.getValues().split(",");
            for (String valueStr : valuesStr) {
                int value = Integer.parseInt(valueStr);
                System.out.println( "First syst Value: " + value + " expectedValue: " + expectedValue);
                if (value != expectedValue) {
                    return false; // Atomic consistency violated
                }
                expectedValue++; // Increment the expected value for the next iteration
            }
            previous = afficheur;
        }
        return true; // Atomic consistency maintained
    }

    /*#****************************************  DIFFUSION SEQUENTIELLE  *************************************/

    @Test
    void initiate_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        Thread.sleep(2000);
        for (ObserverDeCapteur afficheur : afficheurs){         //On s'assure que tous les afficheurs
            assertEquals("1", afficheur.getValues());  // voient la même valeur initial quand on fait le tick
        }
    }

    @Test
    void success_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();     // A1 = A2 = 1
        Thread.sleep(2000);
        capteur.setValue(2);
        capteur.tick();
        assertTrue(isConsistent(afficheurs));   // A1 = A2 = 1,3
        for(int i = 0; i<2; i++) {
            Thread.sleep(2000);
            capteur.tick();
        }
        assertEquals(5, capteur.getValue());
        assertTrue(isConsistent(afficheurs));   // 1,3,4,5
    }

    @Test
    void success_WithSetValues_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();         // A1 = A2 : 1
        Thread.sleep(2000);
        for(int i = 0; i<3; i++) {
            Thread.sleep(2000);     // Introduce a short delay between ticks
            capteur.setValue(i);
            capteur.tick();
        }
        assertTrue(isConsistent(afficheurs));     // A1 = A2 : 1,1,2,3
    }
    @Test
    void fail_IncrementingValues_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();         // A1 = A2 : 1
        Thread.sleep(2000);     // Introduce a short delay between ticks
        capteur.tick();         // A1 = A2 : 1,2
        for(int i = 0; i<3; i++) {
            Thread.sleep(2000);
            capteur.setValue(i);    // When first entering into the 'for' and setValue Capteur = 0
            capteur.tick();         // Capteur = 1, A1 = A2 : 1,2,1
            Thread.sleep(2000);
        }
        assertFalse(isConsistent(afficheurs));     // A1 = A2 : 1,2,1,2,3
    }

    private boolean isConsistent(List<ObserverDeCapteur> afficheurs){
        ObserverDeCapteur previous = afficheurs.get(0);
        for (ObserverDeCapteur afficheur : afficheurs){
            if(! (afficheur.getValues().contains(previous.getValues()) )
                    || !(isIncrementing(afficheur.getValues())) ){
                return false;
            }
            previous = afficheur;
        }
        return  true;
    }

    public boolean isIncrementing(String afficheurValues) {
        String[] numbers = afficheurValues.split(",");
        for (int i = 1; i < numbers.length; i++) {
            int current = Integer.parseInt(numbers[i]);
            int previous = Integer.parseInt(numbers[i - 1]);
            if (current < previous) {
                return false; // Not incrementing
            }
        }
        return true; // Incrementing
    }

}