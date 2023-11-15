package game;

import java.awt.*;
import java.awt.event.*;
import java.io.Console;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        // if contain "console" then run console
        Game game = new Game();

        // if (args.length > 0 && args[0].equals("console")) {
            game.runConsole();
        // } 
        // else {
        //     game.runGraphic();
        // }
    }

    public void runConsole() {
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

    public void runGraphic() {
        Frame gameFrame = new Frame("Tower Defense");
        // Button button = new Button("jeu dans la console");


        gameFrame.setSize(400, 400);
        gameFrame.setVisible(true);


        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });



    }

}