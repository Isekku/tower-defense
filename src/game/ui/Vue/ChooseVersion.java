package game.ui.Vue;

import game.ui.Controller.ChooseVersionController;
import game.ui.State;

import javax.swing.*;
import java.awt.*;

public class ChooseVersion extends JFrame implements State {
    protected JPanel chooseVersionPanel;
        protected JPanel sliderPanel;
            protected JSlider slider;
        protected JPanel buttonPanel;
            protected JPanel tabPanel;
                protected JLabel buttonLabel;
                protected JButton cheminButton;
                protected JButton decorButton;
            protected JPanel modePanel;
                protected JLabel buttonLabel2;
                protected JButton normalButton;
                protected JButton marathonButton;
        protected JButton play;


    private ChooseVersionController controller;
    private static final ChooseVersion instance = new ChooseVersion();

    public ChooseVersion(){
        controller = new ChooseVersionController(this);
        chooseVersionPanel = new JPanel();
        chooseVersionPanel.setLayout(new BorderLayout());
    }

    public void enterState(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // on peut choisir :
        // niveau de difficulté (facile, moyen ou difficile) | slider
        // tableau de jeu (chemins ou décors différents)     | 2 boutons
        // le mode de jeu (normal ou marathon)               | 2 boutons
        Font font = new Font("Comic Sans MS", Font.BOLD, 20);
        // ajout du slider
        JPanel sliderPanel = new JPanel(new GridLayout());
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 2, 0);
                slider.setMajorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                slider.setSnapToTicks(true);
                slider.addChangeListener(e -> controller.changeDifficulty(slider.getValue()));
                // slider label
                JLabel sliderLabel = new JLabel("Difficulté");
                sliderLabel.setHorizontalAlignment(SwingConstants.CENTER);
                sliderLabel.setFont(font);
                sliderLabel.setForeground(Color.BLACK);
                
            sliderPanel.add(sliderLabel, BorderLayout.NORTH);
            sliderPanel.add(slider, BorderLayout.CENTER);
        // ajout des boutons
        JPanel buttonPanel = new JPanel(new BorderLayout());
            JPanel tabPanel = new JPanel(new GridLayout());
                JLabel buttonLabel = new JLabel("Choix du tableau");
                JButton cheminButton = new JButton("Chemin");
                JButton decorButton = new JButton("Décor");
                buttonLabel.setFont(font);
            tabPanel.add(buttonLabel);
            tabPanel.add(cheminButton);
            tabPanel.add(decorButton);

            JPanel modePanel = new JPanel(new GridLayout());
                JLabel buttonLabel2 = new JLabel("Choix du mode de jeu");
                JButton normalButton = new JButton("Normal");
                JButton marathonButton = new JButton("Marathon");
                buttonLabel2.setFont(font);
            modePanel.add(buttonLabel2);
            modePanel.add(normalButton);
            modePanel.add(marathonButton);
                
        buttonPanel.add(tabPanel, BorderLayout.NORTH);
        buttonPanel.add(modePanel, BorderLayout.CENTER);

        JButton play = new JButton("Jouer");
        // il faut que le bouton soit cliquable uniquement si les 3 choix ont été faits
        //play.setEnabled(false);
        

        play.addActionListener(e -> {
            System.out.println("Jouer");
            controller.changeView(game.GameState.PLAYING);
        });


        this.chooseVersionPanel.add(sliderPanel, BorderLayout.NORTH);
        this.chooseVersionPanel.add(buttonPanel, BorderLayout.CENTER);
        this.chooseVersionPanel.add(play, BorderLayout.SOUTH);

    }

    public static ChooseVersion getInstance(){
        return instance;
    }

    public JPanel getView(){
        return chooseVersionPanel;
    }
}
