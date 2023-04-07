package com.banque.banqueInteractive;

import java.nio.ByteBuffer;

public class Banque {

    private String nom;
    private Client[] clients;

    Banque(String nom) {
        this.clients = new Client[1_000_000_000];
        this.nom = nom;
    }

    public Client[] getClients() {
        return this.clients;
    }

    public Client getClient(String nom) {
        Client client = null;
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(nom)) {
                client = clients[i];
                break;
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
        return client;
    }

    public void afficherBilanClient(Client client) {
        Compte[] comptes = client.getComptes();
        byte nbComptes = client.getNbComptes();
        for (int i = 0; i < nbComptes; i++) {
            comptes[i].afficherSolde();
        }
    }

    public void afficherBilanGeneral() {
        for (int i = 0; i < this.clients.length; i++) {
            if (clients[i] != null) {
                this.afficherBilanClient(clients[i]);
            } else {
                break;
            }
        }
    }
}
