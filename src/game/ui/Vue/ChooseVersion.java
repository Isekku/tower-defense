package game.ui.Vue;

import game.ui.Controller.ChooseVersionController;
import game.ui.State;

import javax.swing.*;
import java.awt.*;

public class ChooseVersion extends JFrame implements State {
    public JPanel chooseVersionPanel;
    ChooseVersionController controller = new ChooseVersionController(this);
    private static final ChooseVersion instance = new ChooseVersion();

    public ChooseVersion(){
        chooseVersionPanel = new JPanel();
        chooseVersionPanel.setLayout(new BorderLayout());
    }

    public void enterState(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton button = new JButton("Test");
        chooseVersionPanel.add(button, BorderLayout.CENTER);
    }

    public static ChooseVersion getInstance(){
        return instance;
    }

    public JPanel getView(){
        return chooseVersionPanel;
    }
}
