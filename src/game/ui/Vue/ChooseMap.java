package game.ui.Vue;

import game.ui.Controller.ChooseMapController;
import game.ui.Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.Flow;

public class ChooseMap extends JFrame implements State {
    protected JPanel chooseMapPanel;

    private ChooseMapController controller;
    private static final ChooseMap instance = new ChooseMap();

    public ChooseMap(){
        controller = new ChooseMapController(this);
        chooseMapPanel = new JPanel(new GridLayout(2, 1));
    }

    //Les Panel et les Components besoins pour l'affichage :
    JPanel difficultyPanel = new JPanel();
        JPanel difficultyAndDescriptionLabel = new JPanel();
            JLabel difficultyText = new JLabel("Difficulté : ");
            JSlider difficultySlider = new JSlider();

    JPanel chooseTextPanel = new JPanel();
        JLabel chooseText = new JLabel("Choix de Map :");

    JPanel mapChoicePanel = new JPanel();

        JPanel mapPanel = new JPanel();
            JPanel map1Panel = new JPanel();
                JLabel map1Image = new JLabel();
                JLabel map1Title = new JLabel("Plaine des Vertus");

            JPanel map2Panel = new JPanel();
                JLabel map2Image = new JLabel();
                JLabel map2Title = new JLabel("Voie Royale");

    public void enterState(){
        difficultyPanel.setLayout(new BorderLayout());
            difficultyAndDescriptionLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
                difficultyText.setFont(Style.descriptionFont);
                setSlider(difficultySlider);

                difficultyAndDescriptionLabel.add(difficultyText);
                difficultyAndDescriptionLabel.add(difficultySlider);



            difficultyPanel.add(difficultyAndDescriptionLabel, BorderLayout.CENTER);


        mapChoicePanel.setLayout(new BorderLayout());
            chooseTextPanel.setLayout(new BorderLayout());
                chooseText.setFont(Style.descriptionFont);
                chooseText.setHorizontalAlignment(SwingConstants.CENTER);

                chooseTextPanel.add(chooseText, BorderLayout.CENTER);


            mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 130));
                map1Panel.setLayout(new BoxLayout(map1Panel, BoxLayout.Y_AXIS));
                map1Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    map1Image.setText("Image de plaine");
                    map1Title.setText("Plaine des Vertus");

                    map1Panel.add(map1Image);
                    map1Panel.add(map1Title);

                    mapPanel.add(map1Panel);

                map2Panel.setLayout(new BoxLayout(map2Panel, BoxLayout.Y_AXIS));
                    map2Image.setText("Image de désert");
                    map2Title.setText("Voie Royale");

                    map2Panel.add(map2Image);
                    map2Panel.add(map2Title);

                    mapPanel.add(map2Panel);

            mapChoicePanel.add(chooseTextPanel, BorderLayout.NORTH);
            mapChoicePanel.add(mapPanel, BorderLayout.CENTER);


        chooseMapPanel.add(difficultyPanel, BorderLayout.NORTH);
        chooseMapPanel.add(mapChoicePanel, BorderLayout.CENTER);
    }

    public void quitState(){}

    public void setSlider(JSlider slider){
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinimum(0);
        slider.setMaximum(2);
        slider.setValue(0);
        slider.setPreferredSize(new Dimension(300, 100));
    }

    public static ChooseMap getInstance(){
        return instance;
    }

    public JPanel getView(){
        return this.chooseMapPanel;
    }
}
