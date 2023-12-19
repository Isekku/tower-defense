package game.ui.Vue;

import game.ui.Controller.ChooseMapController;
import game.ui.Style;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChooseMap extends JFrame implements State {
    protected JPanel chooseMapPanel;

    private ChooseMapController controller;
    private static final ChooseMap instance = new ChooseMap();

    public ChooseMap(){
        controller = new ChooseMapController(this);
        chooseMapPanel = new JPanel(new GridLayout(3, 1));
    }

    //Les Panel et les Components besoins pour l'affichage :
    JPanel difficultyPanel = new JPanel();
        JLabel difficultyText = new JLabel("Difficulté : ");
        JSlider difficultySlider = new JSlider();

    JPanel chooseTextPanel = new JPanel();
        JLabel chooseText = new JLabel("Choix de Map :");

    JPanel mapChoicePanel = new JPanel();
        JPanel map1Panel = new JPanel();
            JLabel map1Image = new JLabel();
            JLabel map1Title = new JLabel("Plaine des Vertus");

        JPanel map2Panel = new JPanel();
            JLabel map2Image = new JLabel();
            JLabel map2Title = new JLabel("Voie Royale");

    public void enterState(){
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.X_AXIS));
            difficultyText.setFont(Style.buttonFont);

            difficultySlider.setMinimum(0);
            difficultySlider.setMaximum(2);
            difficultySlider.setValue(0);

            difficultyPanel.add(difficultyText);
            difficultyPanel.add(difficultySlider);

        chooseMapPanel.add(difficultyPanel);
    }

    public void quitState(){}

    public static ChooseMap getInstance(){
        return instance;
    }

    public JPanel getView(){
        return this.chooseMapPanel;
    }
}
