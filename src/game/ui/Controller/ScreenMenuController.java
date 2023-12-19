package game.ui.Controller;

import game.GameState;
import game.ui.Style;
import game.ui.Vue.ScreenMenu;

import javax.swing.*;

public class ScreenMenuController extends Controller{
    public final int PLAY_GAME = 1;
    public final int OPTION_GAME = 2;
    public final int LEAVE_GAME = 3;

    public ScreenMenuController(ScreenMenu view) {
        this.view = view;
    }

    public void mouseClicked(int value) {
        if (value == PLAY_GAME) {
            super.changeView(GameState.VERSION);
        }
        if (value == OPTION_GAME) {
        }
        if (value == LEAVE_GAME){
            System.exit(0);
        }
    }

    public void mouseIsHovering(JButton button){
        Style.buttonIsHovered(button);
    }

    public void mouseIsOut(JButton button){
        Style.buttonIsNormal(button);
    }
}