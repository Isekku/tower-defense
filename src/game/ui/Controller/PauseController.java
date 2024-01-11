package game.ui.Controller;

import javax.swing.*;

import game.GameState;
import game.ui.Vue.Pause;

public class PauseController extends Controller{

    public PauseController(Pause view){
        this.view = view;
    }

    public void mouseClicked(int value) {
        if (value == 1) {
            System.out.println(value);
            changeView(GameState.PLAYING);
        }
        if (value == 2) {
            changeView(GameState.MENU);
        }
    }


}
