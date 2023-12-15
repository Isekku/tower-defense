package game;


import game.ui.*;
import game.ui.Vue.ScreenMenu;
import game.ui.Vue.Vue;

public class Game {
    public static Vue current_view = new Vue(new ScreenMenu());
    public static void main(String[] args) {
        // Map map = new Map(10, 11);
        // System.out.println(map.setSell(5, 5, new Tower(5, 5, 0, 0, 0, 0, 0)));
        // System.out.println(map.setSell(6, 6, new Entity(6, 6, 0, 0, 0)));
        // Model model = new Model();
        Controller controller = new Controller();


        // if contain "console" then run console
        // if (args.length > 0 && args[0].equals("console") || args[0].equals("-terminal") || args[0].equals("--terminal")) {
            //Game.runTerminal(controller);
        // }
        // else {
            Game.runGraphic();
        // }


    }

    public static void runTerminal(Controller controller) {
        // use Terminal class
        Terminal terminal = new Terminal(controller);
    }

    public static void runGraphic() {
        // use Window class
        current_view.affiche();
    }

}