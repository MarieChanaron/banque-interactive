package com.banque.banqueInteractive;

public class Client {

    private String nom;
    private float solde; // Retourne le solde de l'ensemble des comptes
    private Compte[] comptes;
    private byte nbComptes;

    Client(String nom) {
        this.nom = nom;
        this.solde = 0;
        this.nbComptes = 0;
        this.comptes = new Compte[100];
    }

    public String getNom() {
        return this.nom;
    }

    public Compte[] getComptes() {
        return this.comptes;
    }

    public float getSolde() {
        return this.solde;
    }

    public byte getNbComptes() {
        return this.nbComptes;
    }

    public void afficherSolde() {
        System.out.println("Solde global : " + this.getSolde() + " €");
    }

    public void deposer(Compte compte, float valeur) {
        compte.setSolde(+valeur);
        this.solde += valeur;
    }

    public boolean retirer(Compte compte, float valeur) {
        float solde = compte.getSolde();
        boolean succes = false;
        if (solde >= valeur) {
            compte.setSolde(-valeur);
            this.solde -= valeur;
            succes = true;
        }
        return succes;
    }

    public String ajouterCompte() {
        Compte compte = new Compte();
        if (nbComptes == 0) {
            compte.setCourant();
        }
        String iban = compte.getIban();
        comptes[nbComptes] = compte;
        nbComptes ++;
        return iban;
    }
}
