package game.ui.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
import javax.tools.Tool;

import game.ui.Controller.PlayingController;

public class Playing extends JFrame implements State{
    //Tout attributs permettant d'instancier la classe :
    public static boolean isFirstTime = true;
    private static final Playing instance = new Playing();
    private PlayingController controller;
    private final int hauteur = 10;
    private final int largeur = 11;

    protected JPanel playingPanel = new JPanel();
        protected JPanel mapPanel = new JPanel();
            protected JPanel mapGridPanel = new JPanel();
                protected JPanel mapGrid[][] = new JPanel[hauteur][largeur];


        protected JPanel infoPanel = new JPanel();
            protected JLabel moneyLabel = new JLabel("Money : ");
            protected JLabel lifeLabel = new JLabel("Life : ");
            protected JLabel waveLabel = new JLabel("Wave : ");
            protected JLabel timeLabel = new JLabel("Time : ");


    private ImageIcon terreImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources\\assets\\terrain\\Tiles\\FieldsTile_01.png"));

    private ImageIcon herbeImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources\\assets\\terrain\\Tiles\\FieldsTile_38.png"));
    
                       
    //Constructeur unique :
    public Playing(){
        controller = new PlayingController(this);
        playingPanel = new JPanel();
        System.out.println(terreImage.getIconWidth());
    }

    //Les Panels et les Components besoins pour l'affichage :





    //Méthodes propre à la construction de Component plus spécifique :





    //Méthodes permettant d'attribuer les méthodes en fonctino d'action produit sur le bouton :




    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        playingPanel.setLayout(new BorderLayout());
            mapPanel.setLayout(new BorderLayout());
                mapGridPanel.setLayout(new GridLayout(hauteur, largeur));
                    for(int i = 0; i < hauteur; i++){
                        for(int j = 0; j < largeur; j++){
                            mapGrid[i][j] = new JPanel();
                            mapGrid[i][j].setLayout(new BorderLayout());
                            mapGrid[i][j].add(new JLabel(terreImage), BorderLayout.CENTER);
                            mapGridPanel.add(mapGrid[i][j]);

                        }

                    }

                mapPanel.add(mapGridPanel, BorderLayout.CENTER);
            playingPanel.add(mapPanel, BorderLayout.CENTER);

            infoPanel.setLayout(new GridLayout(4, 1));
                infoPanel.add(moneyLabel);
                infoPanel.add(lifeLabel);
                infoPanel.add(waveLabel);
                infoPanel.add(timeLabel);
            playingPanel.add(infoPanel, BorderLayout.SOUTH);



        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }

    public void refresh(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                playingPanel.revalidate();
                playingPanel.repaint();

                controller.refresh();

            }
        });

    }
    
    public void quitState(){
        
    }

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    //Méthodes nécessaire pour l'accessiblité externe de la classe :
    public static Playing getInstance(){
        return instance;
    }

    public JPanel getView() {
        return playingPanel;
    }

    public JLabel getMoneyLabel() {
        return moneyLabel;
    }

    public JLabel getLifeLabel() {
        return lifeLabel;
    }

    public JLabel getWaveLabel() {
        return waveLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

}
