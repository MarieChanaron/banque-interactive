package com.banque.banqueInteractive;

import java.util.UUID;

public class Compte {

    private String iban;
    private float solde;

    Compte() {
        this.iban = Compte.generateIban();
        this.solde = 0;
    }

    public String getIban() {
        return this.iban;
    }

    public float getSolde() {
        return this.solde;
    }

    public void setSolde(float valeur) {
        this.solde = this.solde + valeur;
    }

    public void afficherSolde() {
        System.out.println(this.iban + " : " + this.solde + " €");
    }

    public void virer(float valeur, Compte destinataire) {
        this.solde = this.solde - valeur;
        destinataire.setSolde(valeur);
    }

    public static String generateIban() {
        String iban = UUID.randomUUID().toString();
        return iban;
    }

}