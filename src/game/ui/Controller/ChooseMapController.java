package game.ui.Controller;

import game.GameState;
import game.ui.Vue.ChooseMap;
import game.ui.Vue.State;

import javax.swing.*;

public class ChooseMapController extends Controller{
    public static final int PLAIN_MODE = 1;
    public static final int DESERT_MODE = 2;

    public ChooseMapController(ChooseMap view){
        this.view = view;
    }

    public void mouseClicked(int value){
        if(value == PLAIN_MODE || value == DESERT_MODE){
            Controller.changeView(GameState.PLAYING);
        }
    }

    public void mouseIsHovering(JButton button, JLabel image, ImageIcon icon, int value){
        ChooseMap.buttonIsHovered(button, image, icon, value);
    }

    public void mouseIsOut(JButton button, JLabel imageLabel, ImageIcon icon){
        ChooseMap.buttonIsNormal(button, imageLabel, icon);
    }
}
