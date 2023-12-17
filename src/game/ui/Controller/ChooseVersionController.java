package game.ui.Controller;

import game.Model;
import game.ui.State;
import game.ui.Vue.ChooseVersion;

import javax.swing.*;

public class ChooseVersionController extends Controller {
    private ChooseVersion view;
    private Model model;

    public ChooseVersionController (ChooseVersion view){
        this.view = view;
        this.model = Model.getInstance();
    }

    public void changeDifficulty(int value){
        if (value == 0){
            System.out.println("Facile");
        }
        if (value == 1){
            System.out.println("Moyen");
        }
        if (value == 2){
            System.out.println("Difficile");
        }
        model.setDifficulty(value);
    }

}
