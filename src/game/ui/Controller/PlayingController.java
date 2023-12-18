package game.ui.Controller;

import game.Model;
import game.ui.Vue.Playing;

public class PlayingController extends Controller{
    private Playing view;
    private Model model;

    public PlayingController(Playing view){
        this.view = view;
        this.model = Model.getInstance();
    }

}
