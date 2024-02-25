import com.aoc.Capteur;
import com.aoc.CapteurImpl;
import com.aoc.DiffusionAtomique;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CapteurTest {

    private Capteur capteur;

    private List<ObserverDeCapteur> observerDeCapteurs = new ArrayList<>();

    @BeforeEach
    void setUp(){
        observerDeCapteurs.add(new Afficheur("A1"));
        observerDeCapteurs.add(new Afficheur("A2"));
        this.capteur = new CapteurImpl(observerDeCapteurs, new DiffusionAtomique());
    }


    @Test
    @DisplayName("Tick")
    void tickTest(){
        int initValue =  this.capteur.getValue();
        assertEquals(0, initValue);
        this.capteur.tick();
        int currentValue = this.capteur.getValue();
        assertEquals(1, currentValue);
    }
}
