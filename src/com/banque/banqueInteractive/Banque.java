package com.banque.banqueInteractive;

import java.nio.ByteBuffer;

public class Banque {

    private String nom;
    private Client[] clients;
    private long nbClients;

    Banque(String nom) {
        this.clients = new Client[1_000_000_000];
        this.nom = nom;
        this.nbClients = 0;
    }

    public Client[] getClients() {
        return this.clients;
    }

    public long getNbClients() {
        return this.nbClients;
    }

    public Client getClient(String nom) {
        Client client = null;
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getNom().equals(nom)) {
                client = clients[i];
            }
        }
        return client;
    }

    public Client ajouterClient(String nom) {
        Client client = new Client(nom);
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                clients[i] = client;
                break;
            }
        }
        this.nbClients ++;
        return client;
    }

    public void afficherBilanClient(Client client) {
        System.out.println("\nBILAN DU CLIENT : " + client.getNom());
        Compte[] comptes = client.getComptes();
        int nbComptes = client.getNbComptes();
        System.out.println("==============================================");
        for (int i = 0; i < nbComptes; i++) {
            comptes[i].afficherSolde();
            System.out.println("----------------------------------------------");
        }
        client.afficherSolde();
        System.out.println("==============================================");
    }

    public void afficherBilanGeneral() {
        for (int i = 0; i < this.clients.length; i++) {
            if (clients[i] != null) {
                this.afficherBilanClient(clients[i]);
            } else {
                break;
            }
        }
        float solde = 0;
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null) {
                solde += clients[i].getSolde();
            } else {
                break;
            }
        }
        System.out.println("\nSOLDE GLOBAL : " + solde + "€");
    }
}
