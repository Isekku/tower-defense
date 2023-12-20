package game.ui.Controller;

import game.Game;
import game.GameState;
import game.Model;

import javax.swing.*;

public class Controller {

    JFrame view = null;
    Model model;
    public static void changeView(GameState state){
        Game.game_state.getState().quitState();
        System.out.println("Changing view to: " + state);
        switch (state){
            case MENU: {
                Game.game_state = GameState.MENU;
                break;
            }
            case VERSION: {
                Game.game_state = GameState.VERSION;
                break;
            }
            case PLAYING: {
                Game.game_state = GameState.PLAYING;
                break;
            }
            case MAP: {
                Game.game_state = GameState.MAP;
                break;
            }
            /* case OPTION: {
                break;
            }
            case PAUSE: {
                break;
            }
            case GAMEOVER: {
                break;
            }
            case WIN: {
                break;
            }
            */
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game.screen.setCurrentPanel(Game.game_state.getState().getView());
                Game.screen.revalidate();
                Game.screen.repaint();
            }
        });
    }
}
