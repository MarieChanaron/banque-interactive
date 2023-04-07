package com.banque.banqueInteractive;

import java.util.Scanner;

public class BanqueInteractive {

    static Scanner scanner = new Scanner(System.in);
    static Banque banque = new Banque("Ma banque");



    private static void ajouterClient() {
        System.out.println("\nEntrez le nom du client :");
        String nom = scanner.next();
        Client client = banque.ajouterClient(nom);
        System.out.println("\nLe client " + nom + " a été ajouté.");
        System.out.println("\nVoulez-vous ouvrir un compte pour ce client ?");
        System.out.println("o : oui | n : non");
        String choix = scanner.next();
        if (choix.equals("o")) {
            BanqueInteractive.ouvrirCompte(client);
        }
    }



    public static void ouvrirCompte(Client client) {
        String iban = client.ajouterCompte();
        System.out.println("\nLe compte a été ajouté avec le numéro \n" + iban);
        System.out.println("\nSouhaitez-vous effectuer une opération pour le client " + client.getNom() + " ?");
        System.out.println("o : oui | n : non");
        String choix = scanner.next();
        if (choix.equals("o")) {
            BanqueInteractive.effectuerOperationClient(client);
        }
    }



    public static void afficherBilan(Client client) {
        banque.afficherBilanClient(client);
    }



    public static void effectuerRetrait(Client client) {
        if (client.getComptes()[0] != null && client.getSolde() != 0) {
            System.out.println("Combien souhaite retirer le client " + client.getNom() + " ?");
            float montant = scanner.nextFloat();
            Compte compteCourant = client.getComptes()[0];
            if (client.retirer(compteCourant, montant)) {
                System.out.println("\nNouveau solde du compte courant : " + compteCourant.getSolde() + " €");
            } else {
                System.out.println("\nLe compte courant n'est pas suffisamment approvisionné pour pouvoir effectuer cette opération.");
            }
        } else {
            System.out.println("\nLe compte courant du client n'est pas approvisionné.");
        }
    }



    public static void effectuerDepot(Client client) {
        System.out.println("\nCombien souhaite déposer le client " + client.getNom() + " ?");
        float montant = scanner.nextFloat();
        Compte compteCourant = client.getComptes()[0];
        if (compteCourant != null) {
            client.deposer(compteCourant, montant);
        } else {
            System.out.println("Ce client ne possède pas de compte bancaire.");
        }
        System.out.println("\nNouveau solde du compte courant : " + compteCourant.getSolde() + " €");
    }



    public static void effectuerVirement(Client client) {

        int nbComptes = client.getNbComptes();

        if (nbComptes < 2 || client.getSolde() == 0) {
            System.out.println("\nOpération impossible");
            return;
        }

        Compte[] comptes = client.getComptes();

        System.out.println("\nDe quel compte le/la client(e) " + client.getNom() + " souhaite effectuer un virement ?");
        for (int i = 0; i < nbComptes; i++) {
            System.out.println((i + 1) + ") " + comptes[i].getIban());
        }
        int choix1 = scanner.nextInt();
        Compte origine = comptes[choix1 - 1];
        System.out.println(origine.getIban());

        System.out.println("\nVers quel compte ?");
        Compte[] destinations = new Compte[nbComptes - 1];
        int j = 0;
        for (int i = 0; i < nbComptes; i++) {
            if (!comptes[i].equals(origine)) {
                destinations[j++] = comptes[i];
                System.out.println(j + ") " + comptes[i].getIban());
            }
        }
        int choix2 = scanner.nextInt();
        Compte destination = destinations[choix2 - 1];

        System.out.println("Vous souhaitez effectuer un virement du compte " + origine.getIban() + "\nvers le compte " + destination.getIban() + ".");
        System.out.println("Veuillez saisir le montant :");
        float montant = scanner.nextFloat();

        if (client.retirer(origine, montant)) {
            client.deposer(destination, montant);
        }

    }



    private static void effectuerOperationClient(Client clientParam) {

        if (banque.getNbClients() == 0) {
            System.out.println("\nLa banque ne compte actuellement aucun client.");
            return;
        }

        Client client = clientParam;
        String reponse = "";

        do {
            if (client == null) {
                System.out.println("\nPour quel client ?");
                Client[] clients = banque.getClients();
                for (int i = 0; i < banque.getNbClients(); i++) {
                    System.out.println((i + 1) + ") " + clients[i].getNom());
                }
                client = clients[scanner.nextInt() - 1];
            }

            System.out.println("\nQuelle opération voulez-vous effectuer ?");
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

            System.out.println("\nAvez-vous fini avec le client " + client.getNom() + " ?");
            System.out.println("o : oui | n : non");
            reponse = scanner.next();

        } while (!reponse.equals("o"));

    }



    private static void afficherBilanGeneral() {
        banque.afficherBilanGeneral();
    }



    public static void interagir() {
        System.out.println("\nQuelle opération souhaitez-vous effectuer ?");
        System.out.println("1) Ajouter un client");
        System.out.println("2) Effectuer une opération sur un client");
        System.out.println("3) Afficher un bilan général");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                BanqueInteractive.ajouterClient();
                break;
            case 2:
                BanqueInteractive.effectuerOperationClient(null);
                break;
            case 3:
                BanqueInteractive.afficherBilanGeneral();
                break;
        }
    }

}