package game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame{
    private Button button;

    public Window(String title) {
        super(title);
        this.setSize(400, 400);
        this.setVisible(true);

        button = new Button("jeu dans la console");
        this.add(button);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void update(){
        this.repaint(); // pas sur que ça marche :/
    }
    
}
