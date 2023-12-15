package com.aoc;

public class DiffusionAtomique implements AlgoDiffusion{

    private Capteur capteur;

    @Override
    public void configure(Capteur capteur) {
        this.capteur = capteur;
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
