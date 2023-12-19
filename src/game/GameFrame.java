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
        setVisible(true);

        setCurrentPanel(Game.game_state.getState().getView());

    }

    public void setCurrentPanel(JPanel panel){
        if(currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = panel;
        add(currentPanel, BorderLayout.CENTER);
        Game.game_state.startState();
        repaint();
        revalidate();
    }
}
