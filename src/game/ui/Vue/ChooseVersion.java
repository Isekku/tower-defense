package game.ui.Vue;

import game.ui.Controller.ChooseVersionController;
import game.ui.State;

import javax.swing.*;
import java.awt.*;

public class ChooseVersion extends JFrame implements State {
    ChooseVersionController controller = new ChooseVersionController(this);
    private static final ChooseVersion instance = new ChooseVersion();


    public void enterState(){
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static ChooseVersion getInstance(){
        return instance;
    }
}
