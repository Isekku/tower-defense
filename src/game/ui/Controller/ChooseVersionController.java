package game.ui.Controller;

import game.GameState;
import game.ui.Style;
import game.ui.Vue.ChooseVersion;
import javax.swing.*;

public class ChooseVersionController extends Controller {
    public final int NORMAL_MODE = 1;
    public final int MARATHON_MODE = 2;
    public final int GO_BACK = 3;
    public ChooseVersionController(ChooseVersion view){
        this.view = view;
    }

    public void mouseClicked(int value) {
        if (value == NORMAL_MODE || value == MARATHON_MODE) {
            changeView(GameState.MAP);
            model.setMode(value);
        }
        if (value == GO_BACK) {
            changeView(GameState.MENU);
        }
    }

    public void mouseIsHovering(JButton button){
        Style.buttonIsHovered(button);
    }

    public void mouseIsOut(JButton button){
        Style.buttonIsNormal(button);
    }

}
