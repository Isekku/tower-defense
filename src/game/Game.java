package game;

import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import java.io.IOException;

import javax.swing.*;

public class Game {
    public static void main(String[] args) throws IOException {
        Button consoleButton = new Button("jeu dans la console");
        Button graphucButton = new Button("jeu graphique");
        Frame gameFrame = new Frame("Tower Defense");

        gameFrame.setSize(400, 400);
        gameFrame.setVisible(true);
        gameFrame.setLayout(new GridLayout(2, 1));
        gameFrame.add(consoleButton);
        gameFrame.add(graphucButton);

        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // consoleButton est cliqué
        consoleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("console");
                Console console = System.console();
                if (console == null) {
                    System.err.println("No console.");
                    System.exit(1);
                }
            }
        });





    }

}