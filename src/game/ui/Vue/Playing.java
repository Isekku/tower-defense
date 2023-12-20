package game.ui.Vue;

import javax.swing.*;

import game.ui.Controller.PlayingController;

public class Playing extends JFrame implements State{
    //Tout attributs permettant d'instancier la classe :
    public static boolean isFirstTime = true;
    private static final Playing instance = new Playing();
    private PlayingController controller;

    protected JPanel playingPanel;
        protected JPanel mapPanel;

        protected JPanel infoPanel;
            protected JLabel moneyLabel;
            protected JLabel lifeLabel;
            protected JLabel waveLabel;
            
                

    //Constructeur unique :
    public Playing(){
        controller = new PlayingController(this);
        playingPanel = new JPanel();
    }

    //Les Panels et les Components besoins pour l'affichage :





    //Méthodes propre à la construction de Component plus spécifique :





    //Méthodes permettant d'attribuer les méthodes en fonctino d'action produit sur le bouton :




    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // début du jeu

        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }
    public void refresh(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                playingPanel.revalidate();
                playingPanel.repaint();
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



}
