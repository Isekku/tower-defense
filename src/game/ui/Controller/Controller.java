package game.ui.Controller;

import game.Game;
import game.GameState;
import game.ui.Vue.ChooseVersion;

import javax.swing.*;

public class Controller {

    JFrame view = null;
    public void changeView(GameState state, Controller controller){
        switch (state){
            case MENU: {
                break;
            }
            case VERSION: {
                changeViewToVersion(controller);
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

    public void changeViewToVersion(Controller controller){
        ChooseVersion view = new ChooseVersion();
        controller.view.dispose();
    }
}
