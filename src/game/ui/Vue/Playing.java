package game.ui.Vue;

import javax.swing.JPanel;

import game.ui.Controller.PlayingController;

import javax.swing.JFrame;

public class Playing extends JFrame implements State{
    private static final Playing instance = new Playing();
    private PlayingController controller;
    protected JPanel playingPanel;


    public Playing(){
        controller = new PlayingController(this);
        playingPanel = new JPanel();
    }

    public static Playing getInstance(){
        return instance;
    }

    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // début du jeu
        System.out.println("Entering Playing state");
    }

    public void quitState(){}

    public JPanel getView() {
        return playingPanel;
    }



}
