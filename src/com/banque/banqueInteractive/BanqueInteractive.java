package com.banque.banqueInteractive;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BanqueInteractive {

    static Scanner scanner = new Scanner(System.in);
    static Banque banque = new Banque("Ma banque");



    private static void ajouterClient() {
        System.out.println("\nEntrez le nom du client :");
        String nom = scanner.next();
        Client client = banque.ajouterClient(nom);
        System.out.println("\nLe client " + nom + " a été ajouté.");
        String choix = "";
        while (!(choix.equals("o") || choix.equals("n"))) {
            System.out.println("\nVoulez-vous ouvrir un compte pour ce client ?");
            System.out.println("o : oui | n : non");
            choix = scanner.next();
        }
        if (choix.equals("o")) {
            BanqueInteractive.ouvrirCompte(client);
        }
    }



    public static void ouvrirCompte(Client client) {
        String iban = client.ajouterCompte();
        System.out.println("\nLe compte a été ajouté avec le numéro \n" + iban);
        String choixContinuer = "";
        while (!(choixContinuer.equals("o") || choixContinuer.equals("n"))) {
            System.out.println("\nSouhaitez-vous effectuer une opération pour le client " + client.getNom() + " ?");
            System.out.println("o : oui | n : non");
            choixContinuer = scanner.next();
        }
        if (choixContinuer.equals("o")) {
            BanqueInteractive.effectuerOperationClient(client);
        }
    }



    public static void afficherBilan(Client client) {
        banque.afficherBilanClient(client);
    }



    public static void effectuerRetrait(Client client) {
        if (client.getComptes()[0] != null && client.getSolde() != 0) {
            float montant;
            try {
                System.out.println("\nCombien souhaite retirer le client " + client.getNom() + " ?");
                montant = scanner.nextFloat();
            } catch (InputMismatchException error) {
                System.out.println("Le montant n'est pas valide.");
                return;
            }
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
        float montant = 0;
        try {
            System.out.println("\nCombien souhaite déposer le client " + client.getNom() + " ?");
            montant = scanner.nextFloat();
        } catch (InputMismatchException error) {
            System.out.println("Le montant n'est pas valide.");
            return;
        }
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

        int choix1 = 0;
        try {
            System.out.println("\nDe quel compte le/la client(e) " + client.getNom() + " souhaite effectuer un virement ?");
            for (int i = 0; i < nbComptes; i++) {
                System.out.println((i + 1) + ") " + comptes[i].getIban());
            }
            choix1 = scanner.nextInt();
        } catch (InputMismatchException error) {
            System.out.println("Le choix n'est pas valide.");
            return;
        }
        Compte origine = comptes[choix1 - 1];
        System.out.println(origine.getIban());

        int choix2;
        Compte[] destinations;
        try {
            System.out.println("\nVers quel compte ?");
            destinations = new Compte[nbComptes - 1];
            int j = 0;
            for (int i = 0; i < nbComptes; i++) {
                if (!comptes[i].equals(origine)) {
                    destinations[j++] = comptes[i];
                    System.out.println(j + ")" + comptes[i].getIban());
                }
            }
            choix2 = scanner.nextInt();
        } catch (InputMismatchException error) {
            System.out.println("Le choix n'est pas valide.");
            return;
        }
        Compte destination = destinations[choix2 - 1];

        System.out.println("Vous souhaitez effectuer un virement du compte " + origine.getIban() + "\nvers le compte " + destination.getIban() + ".");
        float montant = 0;
        try {
            System.out.println("Veuillez saisir le montant :");
            montant = scanner.nextFloat();
        } catch (InputMismatchException error) {
            System.out.println("La saisie n'est pas valide.");
            return;
        }
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
        String choixFinOperationClient = "";

        do {
            if (client == null) {
                System.out.println("\nPour quel client ?");
                Client[] clients = banque.getClients();
                for (int i = 0; i < banque.getNbClients(); i++) {
                    System.out.println((i + 1) + ") " + clients[i].getNom());
                }
                client = clients[scanner.nextInt() - 1];
            }

            int choix = 0;
            while (!(choix != 1 || choix != 2 || choix != 3 || choix != 4 || choix != 5)) {
                System.out.println("\nQuelle opération voulez-vous effectuer ?");
                System.out.println("1) Ouvrir un compte");
                System.out.println("2) Afficher un bilan");
                System.out.println("3) Faire un retrait");
                System.out.println("4) Faire un dépôt");
                System.out.println("5) Faire un virement");
                choix = scanner.nextInt();

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
            }

            while (!(choixFinOperationClient.equals("o") || choixFinOperationClient.equals("n"))) {
                System.out.println("\nAvez-vous fini avec le client " + client.getNom() + " ?");
                System.out.println("o : oui | n : non");
                choixFinOperationClient = scanner.next();
            }

        } while (!choixFinOperationClient.equals("o"));

    }



    private static void afficherBilanGeneral() {
        banque.afficherBilanGeneral();
    }



    public static void interagir() {
        int choixOperation = 0;
        try {
            System.out.println("\nQuelle opération souhaitez-vous effectuer ?");
            System.out.println("1) Ajouter un client");
            System.out.println("2) Effectuer une opération sur un client");
            System.out.println("3) Afficher un bilan général");
            choixOperation = scanner.nextInt();
        } catch (InputMismatchException error) {
            System.out.println("La saisie n'est pas valide.");
            return;
        }

        switch (choixOperation) {
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