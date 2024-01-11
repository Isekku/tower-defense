package game.ui.Controller;

import javax.swing.*;

import game.GameState;
import game.ui.Vue.Pause;

public class PauseController extends Controller{

    public PauseController(Pause view){
        this.view = view;
    }

    public void mouseClicked(int value) {
        if (value == 1) { // replendre
            changeView(GameState.PLAYING);
        }
        if (value == 2) { // retour menu principal
            changeView(GameState.MENU);
        }
        if (value == 3) { // sauvagarder
            
        }
        if (value == 4) { // quitter
            System.exit(0);
        }
    }


}
