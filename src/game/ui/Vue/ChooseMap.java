package game.ui.Vue;

import game.ui.Controller.ChooseMapController;
import game.ui.State;

import javax.swing.*;
import java.awt.*;

public class ChooseMap extends JFrame implements State {
    protected JPanel chooseMapPanel = new JPanel(new BorderLayout());

    private ChooseMapController controller;
    private static final ChooseMap instance = new ChooseMap();

    public ChooseMap(){
        controller = new ChooseMapController(this);
        chooseMapPanel = new JPanel();
        chooseMapPanel.setLayout(new BorderLayout());
    }

    public void enterState(){
        chooseMapPanel.add(new JLabel("Test pour voi si cela marche"), BorderLayout.CENTER);
    }

    public void quitState(){}

    public static ChooseMap getInstance(){
        return instance;
    }

    public JPanel getView(){
        return this.chooseMapPanel;
    }
}
