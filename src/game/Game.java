package game;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    public static void main(String[] args) {
        Button button = new Button("click zdsijcodsijcdsiocj");
        Frame gameFrame = new Frame("Tower Defense");
        gameFrame.setSize(400, 400);
        gameFrame.setVisible(true);
        gameFrame.add(button);

        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

     

    

}