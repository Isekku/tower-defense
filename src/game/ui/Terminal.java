package game.ui;

import java.io.Console;
import game.map.Map;

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
        System.out.println("mode console");
        System.out.println(Map.getInstance());
    }

    public void update() {
        // clear screen
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
    }



}
