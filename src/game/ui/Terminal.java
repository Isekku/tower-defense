package game.ui;

import java.io.Console;

import game.View;
import game.map.Map;

public class Terminal implements View{
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
        //controller.printMap();
    }

    public void update(Map map){
        // clear screen
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
        // print map
        System.out.println(map);
    }
    



}
