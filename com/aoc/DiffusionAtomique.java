package com.aoc;

public class DiffusionAtomique implements AlgoDiffusion{


    private Capteur capteur;

    private DiffusionAtomique(Capteur capteur){
        this.capteur = capteur;
    }

    @Override
    public void configure() {

    }

    @Override
    public void execute() {

    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
}
