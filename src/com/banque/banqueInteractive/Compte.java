package com.banque.banqueInteractive;

import java.util.UUID;

public class Compte {

    private String iban;
    private float solde;
    boolean courant = false;


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

    public void setCourant() {
        this.courant = true;
    }

    public void afficherSolde() {
        if (this.courant) {
            System.out.println("Compte courant :");
        }
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