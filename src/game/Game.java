package game;

import game.map.Map;
import game.ui.Terminal;
import game.ui.Window;
import game.geometry.Coordinates;

public class Game {
    public static void main(String[] args) {
        // if contain "console" then run console


        // if (args.length > 0 && args[0].equals("console") || args[0].equals("-terminal") || args[0].equals("--terminal")) {
            Game.runTerminal();
        // }
        // else {
            Game.runGraphic();
        // }

        Map map = new Map(30, 13);
        System.out.println(map);
        System.out.println(Coordinates.coordonateToPoint("E5"));
    }

    public static void runTerminal() {
        // use Terminal class
        Terminal terminal = new Terminal();
    }

    public static void runGraphic() {
        // use Window class
        Window window = new Window("Tower Defense");

    }

}