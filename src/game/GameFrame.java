package game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private JPanel currentPanel;

    public GameFrame(){
        super("Plants Vs Zombie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setCurrentPanel(Game.game_state.getState().getView());
        setVisible(true);
    }

    public void setCurrentPanel(JPanel panel){
        if(currentPanel != null) {
            remove(currentPanel);
            System.gc();
        }
        currentPanel = panel;
        add(currentPanel, BorderLayout.CENTER);
        if(Game.game_state.getState().isFirstTime()){
            Game.game_state.startState();
            Game.game_state.getState().notFirstTime();
        }
    }
}
