package game.ui.Vue;

import javax.swing.*;

import game.ui.Controller.PlayingController;

public class Playing extends JFrame implements State{
    private static final Playing instance = new Playing();
    private PlayingController controller;
    protected JPanel playingPanel;
    public static boolean isFirstTime = true;


    public Playing(){
        controller = new PlayingController(this);
        playingPanel = new JPanel();
    }

    public static Playing getInstance(){
        return instance;
    }

    public static int compte = 1;

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

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    public void quitState(){
    }

    public JPanel getView() {
        return playingPanel;
    }



}
