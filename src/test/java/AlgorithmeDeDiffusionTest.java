import com.aoc.*;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
    }

    @AfterEach
    void SetUpDisplayObservers() {
        for (ObserverDeCapteur afficheur : this.afficheurs) {
            System.out.println("" + afficheur.getName() + ": " + afficheur.getValues());
        }
    }

    /*#****************************************  DIFFUSION ATOMIQUE  *************************************/

    @Test
    void initialCase_testAtomicConsistency(){
        for (ObserverDeCapteur afficheur : afficheurs){         //On s'assure que tous les afficheurs
            assertEquals("1", afficheur.getValues());  // ont la même valeur initial quand on fait le tick
        }
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }

    @Test
    void successCase_AtomicConsistency() {
        // Validate atomic consistency
        assertTrue(isAtomicConsistent(getObservedValues(capteur,2)));
    }

    @Test
    void failCase_AtomicConsistency() {
        // Set up scenario
        capteur.setValue(2);
        System.out.println(" Enter  " );
        // Validate atomic consistency
        assertFalse(isAtomicConsistent(getObservedValues(capteur, 2))); // A2 = 1, 3, 4
        assertFalse(isAtomicConsistent(getObservedValues(capteur, 2))); // A3 = 1, 2, 5
    }

    private int[] getObservedValues(Capteur capteur, int ticks) {
        int[] observedValues = new int[ticks];
        for (int i = 0; i < ticks; i++) {
            observedValues[i] = capteur.getValue();

            capteur.tick(); // Move to the next tick
        }
        return observedValues;
    }
    private boolean isAtomicConsistent(int[] afficheurs) {
        int expectedValue = 1; // Start with the expected value of 1
        for (int currentValue : afficheurs) {
            if (currentValue != expectedValue) {
                return false; // Atomic consistency violated
            }
            expectedValue++; // Increment the expected value for the next iteration
        }
        return true; // Atomic consistency maintained
    }


    /*#****************************************  DIFFUSION SEQUENTIELLE  *************************************/

    @Test
    void initialCase_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        assertEquals(1, capteur.getValue());
        Thread.sleep(2000);
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }
    @Test
    void successCase_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        assertTrue(isSequentiallyConsistent(capteur, 2));
    }

    @Test
    void successCase_SequentialConsistency2() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.tick();
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.setValue(3);
        assertTrue(isSequentiallyConsistent(capteur, 3)); // Expected to fail
    }

    @Test
    void failCase_SequentialConsistency() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick(); // First tick
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.setValue(3); // Set value to 3 before the second tick
        capteur.tick(); // Second tick
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.setValue(2); // Set value to 2 before the third tick
        assertFalse(isSequentiallyConsistent(capteur, 3)); // Expected to fail
    }


    /**
     * Checks if the sequence of values observed from the Capteur is sequentially consistent.
     *
     * @param capteur The Capteur, instance to observe.
     * @param ticks   The number of ticks to observe.
     * @return true if the observed sequence is sequentially consistent, false otherwise.
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    private boolean isSequentiallyConsistent(Capteur capteur, int ticks) throws InterruptedException {
        Set<Integer> observedValues = new HashSet<>();
        int previousValue = capteur.getValue();
        int currentValue;
        observedValues.add(previousValue);
        for (int i = 1; i <= ticks; i++) {
            capteur.tick();
            Thread.sleep(2000); // Introduce a short delay between ticks
            currentValue = capteur.getValue();
            observedValues.add(currentValue);
            if (currentValue <= previousValue) {
                return false; // Sequence is not monotonically increasing
            }
            previousValue = currentValue;
        }
        System.out.println(observedValues);
        return true; // All observed values form a monotonically increasing sequence
    }



    /*#****************************************  DIFFUSION PAR EPOQUE  *************************************/

    @Test
    void diffusionParEpoqueTest(){
        this.capteur = new CapteurImpl(afficheurs, diffusionEpoque);
        capteur.tick();
        assertEquals(0, capteur.getValue());
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }
}