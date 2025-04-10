package game;

import game.ui.Vue.State;
import game.ui.Vue.ChooseMap;
import game.ui.Vue.ChooseVersion;
import game.ui.Vue.Pause;
import game.ui.Vue.Playing;
import game.ui.Vue.ScreenMenu;

public enum GameState {
    MENU(ScreenMenu.getInstance()),
    VERSION(ChooseVersion.getInstance()),
    PLAYING(Playing.getInstance()),
    MAP(ChooseMap.getInstance()),
    PAUSE(Pause.getInstance());
    //OPTION,
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
