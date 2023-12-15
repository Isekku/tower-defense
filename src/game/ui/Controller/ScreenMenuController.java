package game.ui.Controller;

import game.GameState;
import game.ui.Vue.ScreenMenu;

import javax.swing.*;
import java.awt.*;

public class ScreenMenuController extends Controller{
    public final int PLAY_GAME = 1;
    public final int OPTION_GAME = 2;
    public final int LEAVE_GAME = 3;

    public ScreenMenuController(ScreenMenu view) {
        this.view = view;
    }

    public void mouseClicked(int value) {
        if (value == PLAY_GAME) {
            super.changeView(GameState.VERSION, this);
        }
        if (value == OPTION_GAME) {
        }
        if (value == LEAVE_GAME){
            System.exit(0);
        }
    }

    public void mouseIsHovering(JButton button){
        System.out.println("Arrête de me toucher");
        button.setForeground(Color.YELLOW);
    }

    public void mouseIsOut(JButton button){
        System.out.println("Enfin");
        button.setForeground(Color.BLACK);
    }
}