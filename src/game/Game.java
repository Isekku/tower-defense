package game;

import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import javax.swing.*;

import game.ui.Terminal;
import game.ui.Window;

public class Game {
    public static void main(String[] args) {
        // if contain "console" then run console
        Game game = new Game();

        // if (args.length > 0 && args[0].equals("console")) {
            game.runTerminal();
        // } 
        // else {
        //     game.runGraphic();
        // }
    }

    public void runTerminal() {
        // use Terminal class
        Terminal terminal = new Terminal();
    }

    public void runGraphic() {
        // use Window class
        Window window = new Window("Tower Defense");

    }

}