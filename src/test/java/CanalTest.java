import com.aoc.AlgoDiffusion;
import com.aoc.Capteur;
import com.aoc.CapteurImpl;
import com.aoc.DiffusionAtomique;
import com.aoc.proxy.Canal;
import com.aoc.proxy.ObserverDeCapteurAsync;
import com.aoc.servent.Afficheur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.Future;

public class CanalTest {
    private Capteur capteur;

    private ObserverDeCapteurAsync canal;

    @BeforeEach
    void setUp(){
        AlgoDiffusion atomicDiffusion = new DiffusionAtomique();
        capteur = new CapteurImpl(atomicDiffusion);
        canal = new Canal(atomicDiffusion, new Afficheur("A1"));
        capteur.attach(canal);
    }

    @Test
    @DisplayName("Update")
    void update(){
        Future<Void> future = this.canal.update(canal);
        assertNotNull(future);
    };

}
