package game;


import game.ui.*;

public class Game {
    public static GameState game_state = GameState.MENU;
    public static Model model;
    public static GameFrame screen;
    public static void main(String[] args) {
        // Map map = new Map(10, 11);
        // System.out.println(map.setSell(5, 5, new Tower(5, 5, 0, 0, 0, 0, 0)));
        // System.out.println(map.setSell(6, 6, new Entity(6, 6, 0, 0, 0)));
        // Model model = new Model();


        // if contain "console" then run console
        // if (args.length > 0 && args[0].equals("console") || args[0].equals("-terminal") || args[0].equals("--terminal")) {
            //Game.runTerminal(controller);
        // }
        Game.runGraphic();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                model = new Model();
                screen = new GameFrame();
                screen.setCurrentPanel(game_state.getState().getView());
                screen.revalidate();
            }
        });


    }

    public static void runTerminal() {
        // use Terminal class
        Terminal terminal = new Terminal();
    }

    public static void runGraphic() {
        // use Window class
    }

}