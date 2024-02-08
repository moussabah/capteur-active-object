package com.aoc.test;

import com.aoc.*;
import com.aoc.servent.Afficheur;
import com.aoc.servent.ObserverDeCapteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlgoDiffusionTest{

    private AlgoDiffusion diffusionAto, diffusionEpoque, diffusionSeq;
    private Capteur capteur;
    private List<ObserverDeCapteur> afficheurs;



    @BeforeEach
    void SetUp() {
        this.diffusionAto = new DiffusionAtomique();
        this.diffusionEpoque = new DiffusionEpoque();
        this.diffusionSeq = new DiffusionSequentielle();

        this.afficheurs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            afficheurs.add(new Afficheur("A" + i));
        }
        assertTrue(afficheurs.size() == 5);
    }

    /*#****************************************  DIFFUSION ATOMIQUE  *************************************/

    @Test
    void diffusionAtoTest(){
        this.capteur = new CapteurImpl(afficheurs, diffusionAto);
        capteur.tick();
        assertEquals(1, capteur.getValue(), "Should return value = 1");
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }

    /*#****************************************  DIFFUSION SEQUENTIELLE  *************************************/

    @Test
    void diffusionSeqTest(){
        this.capteur = new CapteurImpl(afficheurs, diffusionSeq);
        capteur.tick();
        assertEquals(1, capteur.getValue());
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }

    /*#****************************************  DIFFUSION PAR EPOQUE  *************************************/

    @Test
    void diffusionParEpoqueTest(){
        this.capteur = new CapteurImpl(afficheurs, diffusionEpoque);
        capteur.tick();
        assertEquals(1, capteur.getValue());
        capteur.tick();
        assertEquals(2, capteur.getValue());
    }
}