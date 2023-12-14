package game.ui.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import game.Controller;
import game.View;
import game.map.Map; // à changer 

public class Window extends JFrame implements View{
    private JPanel carte;
    private Button button;

    public Window(String title, Controller controller) {
        super(title);
        this.setSize(400, 400);
        this.setVisible(true);

        carte = new JPanel();
        carte.setBackground(Color.GRAY); // test temporaire
        getContentPane().add(carte);

        button = new Button("jeu dans la console");
        getContentPane().add(button);

        getContentPane().setLayout(new GridLayout());

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }

    public void update(Map map){
        this.repaint(); // pas sur que ça marche :/
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map.isNull(i, j)){
                    carte.add(new JLabel("test")); // test mais ça marche pas  il faut peut etre mettre null à la place de d'une cellule vide
                }
            }
        }
    }
    
}
