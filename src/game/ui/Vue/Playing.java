package game.ui.Vue;

import javax.swing.JPanel;

import game.ui.State;
import javax.swing.JFrame;

public class Playing extends JFrame implements State{
    private static final Playing instance = new Playing();
    protected JPanel playingPanel;

    public Playing(){
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

    public JPanel getView() {
        return playingPanel;
    }


}
