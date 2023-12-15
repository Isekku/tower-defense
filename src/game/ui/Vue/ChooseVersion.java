package game.ui.Vue;

import game.ui.Controller.ChooseVersionController;

import javax.swing.*;
import java.awt.*;

public class ChooseVersion extends JFrame {
    ChooseVersionController controller = new ChooseVersionController(this);

    public ChooseVersion(){
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
