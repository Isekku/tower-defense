package game;

import game.map.Map;
import game.mobs.entities.Entity;
import game.mobs.towers.Tower;
import game.ui.Terminal;
import game.ui.Window;
import game.geometry.Coordinates;

public class Game {
    public static void main(String[] args) {
        // Map map = new Map(10, 11);
        // System.out.println(map.setSell(5, 5, new Tower(5, 5, 0, 0, 0, 0, 0)));
        // System.out.println(map.setSell(6, 6, new Entity(6, 6, 0, 0, 0)));
        // Model model = new Model();
        Controller controller = new Controller();


        // if contain "console" then run console
        // if (args.length > 0 && args[0].equals("console") || args[0].equals("-terminal") || args[0].equals("--terminal")) {
            Game.runTerminal(controller);
        // }
        // else {
            Game.runGraphic(controller);
        // }


    }

    public static void runTerminal(Controller controller) {
        // use Terminal class
        Terminal terminal = new Terminal(controller);
    }

    public static void runGraphic(Controller controller) {
        // use Window class
        Window window = new Window("Tower Defense",controller);

    }

}