package game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame{
    public Window(String title) {
        Frame gameFrame = new Frame(title);
        Button button = new Button("jeu dans la console");
        gameFrame.add(button);


        gameFrame.setSize(400, 400);
        gameFrame.setVisible(true);

        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
    
}
