package game.ui.Controller;

import game.Game;
import game.GameState;
import game.Model;
import game.ui.State;
import game.ui.Vue.ChooseVersion;

import javax.swing.*;

public class Controller {

    JFrame view = null;
    Model model;
    public void changeView(GameState state){
        switch (state){
            case MENU: {
                break;
            }
            case VERSION: {
                Game.game_state = GameState.VERSION;
                Game.screen.setCurrentPanel(Game.game_state.getState().getView());
                Game.screen.repaint();
                Game.screen.revalidate();
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
    }
}
