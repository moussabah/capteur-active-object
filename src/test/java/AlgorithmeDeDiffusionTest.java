import com.aoc.*;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
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
        assertTrue(afficheurs.isEmpty());
        for (int i = 0; i < 2; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }
        assertTrue(afficheurs.size() == 2);
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
    }

    /*#****************************************  DIFFUSION ATOMIQUE  *************************************/

    @Test
    void diffusionAtoTest(){
        for (ObserverDeCapteur afficheur : afficheurs){         //On s'assure que tous les afficheurs
            assertEquals(1, capteur.getValue());        // ont la même valeur
        }
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }

    @Test
    void testAtomicConsistencyOracle() {
        // Get observed values for each afficheur
        int[] observedValues = new int[4];
        for (int i = 0; i < 4; i++) {
            observedValues[i] = capteur.getValue();
            capteur.tick();
        }
        // Validate atomic consistency
        assertEquals(true, isAtomicConsistent(observedValues));
    }

    @Test
    void testAtomicConsistency2() {
        // Set up scenario
        System.out.println(capteur.getValue());
        capteur.setValue(1); // Set initial value to 1
        System.out.println(capteur.getValue());

        capteur.tick();
        capteur.setValue(3);
        capteur.tick();

        // Get observed values for each afficheur through the capteur
        int[] observedValues = new int[4];
        for (int i = 0; i < 4; i++) {
            observedValues[i] = capteur.getValue();
            capteur.tick(); // Move to the next tick
        }
        // Validate atomic consistency
        assertEquals(false, isAtomicConsistent(observedValues));
    }

    @Test
    void testAtomicConsistency3() {
        // Set up scenario
        capteur.setValue(1);
        capteur.tick();
        capteur.setValue(3);
        capteur.tick();
        capteur.setValue(5);
        capteur.tick();

        // Get observed values for each afficheur through the capteur
        int[] observedValues = new int[4];
        for (int i = 0; i < 4; i++) {
            observedValues[i] = capteur.getValue();
            capteur.tick(); // Move to the next tick
        }

        // Validate atomic consistency
        assertEquals(false, isAtomicConsistent(observedValues));
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
    void diffusionSeqTest() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        assertEquals(1, capteur.getValue());
        Thread.sleep(2000);
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }
    @Test
    void testSequentialConsistencyScenario1() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.tick();
        assertTrue(isSequentiallyConsistent(capteur, 2));
    }

    @Test
    void testSequentialConsistencyScenario2() throws InterruptedException {
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.setValue(3);
        capteur.tick();
        Thread.sleep(2000); // Introduce a short delay between ticks
        capteur.setValue(5);
        assertFalse(isSequentiallyConsistent(capteur, 3)); // Expected to fail
    }

    private boolean isSequentiallyConsistent(Capteur capteur, int ticks) throws InterruptedException {
        Set<Integer> observedValues = new HashSet<>();
        for (int i = 0; i < ticks; i++) {
            observedValues.add(capteur.getValue());
            capteur.tick();
            Thread.sleep(2000); // Introduce a short delay between ticks
        }
        return isIncreasingSequence(observedValues);
    }

    private boolean isIncreasingSequence(Set<Integer> values) {
        int expectedValue = 1;
        for (int value : values) {
            if (value != expectedValue) {
                return false;
            }
            expectedValue++;
        }
        return true;
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