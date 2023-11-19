package game.ui;

import java.io.Console;

public class Terminal {
    public Terminal(){
        System.out.println("console");
        Console console = System.console();
        int longueur = 80;
        int hauteur = 18;
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        for(int i = 0; i < longueur; i++) {
            System.out.print("*");
        }
        for(int i = 0; i < hauteur; i++) {
            System.out.print("\n*");
            for(int j = 0; j < longueur-2; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
        }
        System.out.println();
        for(int i = 0; i < longueur; i++) {
            System.out.print("*");
        }

    }
    
}
