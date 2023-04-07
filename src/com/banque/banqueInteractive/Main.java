package com.banque.banqueInteractive;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String choix = "o";

        while (choix == "o") {
            BanqueInteractive.interagir();
            System.out.println("Voulez-vous faire autre chose ?");
            System.out.println("o : oui | n : non");
            choix = scanner.next();
        }

        scanner.close();

    }
}