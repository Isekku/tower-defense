package game;


import game.ui.*;

public class Game {
    public static GameState game_state = GameState.MENU;
    public static Model model;
    public static GameFrame screen;
    public static void main(String[] args) {
        if(true || args.length != 0 && (args[0].equals("-terminal") || args[0].equals("--terminal"))){
            runTerminal();
        }
        else{
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("c moua");
                    model = Model.getInstance();
                    screen = new GameFrame();
                    screen.setCurrentPanel(game_state.getState().getView());
                    model.print();
                }
            });
        }
    }

    public static void runTerminal() {
        // use Terminal class
        Terminal terminal = new Terminal();
        terminal.play();
    }

    public static void runGraphic() {
        // use Window class
    }

}