package com.banque.banqueInteractive;

import java.util.Scanner;

public class BanqueInteractive {

    static Scanner scanner = new Scanner(System.in);
    static Banque banque = new Banque("Ma banque");

    private static void ajouterClient() {
        System.out.println("Entrez le nom du client :");
        String nomNouveauClient = scanner.next();
        banque.ajouterClient(nomNouveauClient);
        System.out.println("Le client " + nomNouveauClient + " a été ajouté.");
        System.out.println("Voulez-vous effectuer une opération pour ce client ?");
        System.out.println("o : oui | n : non");
        String choix = scanner.next();
        if (choix.equals("o")) {
            BanqueInteractive.effectuerOperationClient(nomNouveauClient);
        }
    }

    private static void effectuerOperationClient(String nomNouveauClient) {
        String nom;
        if (nomNouveauClient.length() == 0) {
            System.out.println("Entrez le nom du client");
            nom = scanner.next();
        } else {
            nom = nomNouveauClient;
        }
        Client client = banque.getClient(nom);
        System.out.println("Quelle opération voulez-vous effectuer ?");
        System.out.println("1) Afficher un bilan");
        System.out.println("2) Faire un retrait");
        System.out.println("3) Faire un dépôt");
        System.out.println("3) Faire un virement");
        String choix = scanner.next();

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
                BanqueInteractive.ajouterClient();
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