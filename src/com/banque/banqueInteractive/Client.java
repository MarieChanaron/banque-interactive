package com.banque.banqueInteractive;

public class Client {

    private String nom;
    private float solde; // Retourne le solde de l'ensemble des comptes
    private Compte[] comptes;
    private byte nbComptes;

    Client(String nom) {
        this.nom = nom;
        this.nbComptes = 0;
        this.solde = 0;
        this.comptes = new Compte[100];
        this.ajouterCompte();
    }

    public String getNom() {
        return this.nom;
    }

    public Compte[] getComptes() {
        return this.comptes;
    }

    public float getSolde() {
        float solde = 0;
        for (int i = 0; i < nbComptes; i++) {
            solde += comptes[i].getSolde();
        }
        return solde;
    }

    public byte getNbComptes() {
        return this.nbComptes;
    }

    public void afficherSolde() {
        System.out.println("Le solde global est : " + this.getSolde());
    }

    public void deposer(Compte compte, float valeur) {

    }

    public void retirer(Compte compte, float valeur) {

    }

    public void ajouterCompte() {
        Compte compte = new Compte();
        comptes[nbComptes] = compte;
        nbComptes ++;
    }
}
