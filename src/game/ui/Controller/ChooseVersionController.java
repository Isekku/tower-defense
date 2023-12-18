package game.ui.Controller;

import game.GameState;
import game.Model;
import game.ui.State;
import game.ui.Vue.ChooseMap;
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
            super.changeView(GameState.MAP);
        }
        if (value == GO_BACK) {
            super.changeView(GameState.MENU);
        }
    }

}
