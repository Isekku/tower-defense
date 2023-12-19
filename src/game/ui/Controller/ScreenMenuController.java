package game.ui.Controller;

import game.GameState;
import game.ui.Vue.ScreenMenu;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
            super.changeView(GameState.VERSION);
        }
        if (value == OPTION_GAME) {
        }
        if (value == LEAVE_GAME){
            System.exit(0);
        }
    }

    public void mouseIsHovering(JButton button){
        button.setForeground(Color.GRAY);
        Border line = new LineBorder(Color.GRAY,5, true);
        Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));
        button.setBorder(compound);
    }

    public void mouseIsOut(JButton button){
        button.setForeground(Color.BLACK);
        Border line = new LineBorder(Color.BLACK,5, true);
        Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));
        button.setBorder(compound);
    }
}