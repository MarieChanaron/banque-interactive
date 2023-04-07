package com.banque.banqueInteractive;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String choix = "o";

        System.out.println("\nBonjour !");

        while (choix.equals("o")) {
            BanqueInteractive.interagir();
            System.out.println("\nVoulez-vous faire autre chose ?");
            System.out.println("o : oui | n : non");
            choix = scanner.next();
            if (choix.equals("n")) {
                System.out.println("\nAu revoir !");
            }
        }

        scanner.close();

    }
}