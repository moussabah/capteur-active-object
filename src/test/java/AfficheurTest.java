import com.aoc.AlgoDiffusion;
import com.aoc.Capteur;
import com.aoc.CapteurImpl;
import com.aoc.DiffusionAtomique;
import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AfficheurTest {
    private ObserverDeCapteur afficheur;
    private ObserverDeCapteurAsync canal;

    private Capteur capteur;

    @BeforeEach
    void setUp(){
        AlgoDiffusion algoDiffusion = new DiffusionAtomique();
        this.capteur = new CapteurImpl(new ArrayList<>(), algoDiffusion);
        this.afficheur = new Afficheur("A1");
        this.canal = new Canal(algoDiffusion, this.afficheur);

        this.capteur.attach(canal);
    }

    @Test
    @DisplayName("GetName")
    void getNameTest(){
        assertEquals("A1", afficheur.getName());
    }


    @Test
    @DisplayName("update")
    void update(){
        int initValue = ((Afficheur) this.afficheur).getCurrentValue();
        assertEquals(0, initValue);
        this.capteur.increment();
        this.afficheur.update(canal);
        int currentValue = ((Afficheur) this.afficheur).getCurrentValue();
        assertEquals(1, currentValue);
    }

    @Test
    @DisplayName("Value read")
    void getValuesTest(){
        this.capteur.tick();//1
        this.capteur.tick();//2
        this.capteur.tick();//3

        assertEquals("1,2,3", this.afficheur.getValues());
    }

}
