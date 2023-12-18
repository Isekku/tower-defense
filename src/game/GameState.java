package game;

import game.ui.State;
import game.ui.Vue.ChooseMap;
import game.ui.Vue.ChooseVersion;
import game.ui.Vue.Playing;
import game.ui.Vue.ScreenMenu;

import javax.swing.*;

public enum GameState {
    MENU(ScreenMenu.getInstance()),
    VERSION(ChooseVersion.getInstance()),
    PLAYING(Playing.getInstance()),
    MAP(ChooseMap.getInstance());
    //OPTION,
    //PAUSE,
    //GAMEOVER,
    //WIN;

    private State current_state;

    private GameState(State state){
        current_state = state;
    }

    public State getState(){
        return current_state;
    }

    public void startState(){
        current_state.enterState();
    }
}
