package com.banque.banqueInteractive;

import java.util.Scanner;

public class BanqueInteractive {

    static Scanner scanner = new Scanner(System.in);
    static Banque banque = new Banque("Ma banque");



    private static void ajouterClient(String nomNouveauClient) {
        if (nomNouveauClient == null) {
            System.out.println("Entrez le nom du client :");
            nomNouveauClient = scanner.next();
        }
        banque.ajouterClient(nomNouveauClient);
        System.out.println("Le client " + nomNouveauClient + " a été ajouté.");
        System.out.println("Voulez-vous effectuer une opération pour ce client ?");
        System.out.println("o : oui | n : non");
        String choix = scanner.next();
        if (choix.equals("o")) {
            BanqueInteractive.effectuerOperationClient(nomNouveauClient);
        }
    }



    public static void ouvrirCompte(Client client) {
        String iban = client.ajouterCompte();
        System.out.println("Le compte a été ajouté avec le numéro " + iban);
    }



    public static void afficherBilan(Client client) {
        Compte[] comptes = client.getComptes();
        int nbComptes = client.getNbComptes();
        System.out.println("==============================================");
        for (int i = 0; i < nbComptes; i++) {
            comptes[i].afficherSolde();
            System.out.println("---------------------------------------------");
        }
        client.afficherSolde();
        System.out.println("=============================================");
    }



    public static void effectuerRetrait(Client client) {}
    public static void effectuerDepot(Client client) {}
    public static void effectuerVirement(Client client) {}



    private static void effectuerOperationClient(String nomNouveauClient) {
        String nom;
        if (nomNouveauClient.length() == 0) {
            System.out.println("Entrez le nom du client");
            nom = scanner.next();
        } else {
            nom = nomNouveauClient;
        }
        try {
            Client client = banque.getClient(nom);
            System.out.println("Quelle opération voulez-vous effectuer ?");
            System.out.println("1) Ouvrir un compte");
            System.out.println("2) Afficher un bilan");
            System.out.println("3) Faire un retrait");
            System.out.println("4) Faire un dépôt");
            System.out.println("5) Faire un virement");
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    BanqueInteractive.ouvrirCompte(client);
                    break;
                case 2:
                    BanqueInteractive.afficherBilan(client);
                    break;
                case 3:
                    BanqueInteractive.effectuerRetrait(client);
                    break;
                case 4:
                    BanqueInteractive.effectuerDepot(client);
                    break;
                case 5:
                    BanqueInteractive.effectuerVirement(client);
                    break;
            }
        } catch (NullPointerException error) {
            System.out.println("Ce client n'existe pas.");
            System.out.println("Souhaitez-vous ajouter cette personne comme nouveau client ?");
            System.out.println("o : oui | n : non");
            String choix = scanner.next();
            if (choix.equals("o")) {
                BanqueInteractive.ajouterClient(nom);
            }
        }

    }



    private static void afficherBilanGeneral() {
        banque.afficherBilanGeneral();
    }



    public static void interagir() {
        System.out.println("Quelle opération voulez-vous effectuer ?");
        System.out.println("1) Ajouter un client");
        System.out.println("2) Effectuer une opération sur un client");
        System.out.println("3) Afficher un bilan général");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                BanqueInteractive.ajouterClient(null);
                break;
            case 2:
                BanqueInteractive.effectuerOperationClient("");
                break;
            case 3:
                BanqueInteractive.afficherBilanGeneral();
                break;
        }
    }

}