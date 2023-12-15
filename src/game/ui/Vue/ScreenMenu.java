package game.ui.Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import game.Controller;
import game.View;
import game.map.Map; // à changer
import game.ui.Controller.ScreenMenuController;

public class ScreenMenu extends JFrame{

    ScreenMenuController controller = new ScreenMenuController(this);
    Container screen = this.getContentPane();
    Dimension buttonDimension = new Dimension(300, 100);

    public ScreenMenu() {
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Création et gestion du Panel principale
        JPanel mainMenu = new JPanel(new GridLayout(2, 1));

            JPanel logoPanel = new JPanel(new BorderLayout());
                JLabel logoLabel = new JLabel("Plants vs Zombie");
                    logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    logoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
                logoPanel.add(logoLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
                JButton playButton = new JButton("Jouer");
                playButton.addChangeListener((event) -> controller.mouseClicked(controller.PLAY_GAME));
                addMouseListener(playButton);

                JButton optionButton = new JButton("Option");
                optionButton.addChangeListener((event) -> controller.mouseClicked(controller.OPTION_GAME));
                addMouseListener(optionButton);

                JButton quitButton = new JButton("Quitter");
                quitButton.addChangeListener((event) -> controller.mouseClicked(controller.LEAVE_GAME));
                addMouseListener(quitButton);

                buttonPanel.add(createButton(playButton));
                buttonPanel.add(createButton(optionButton));
                buttonPanel.add(createButton(quitButton));

        mainMenu.add(logoPanel);
        mainMenu.add(buttonPanel);

        screen.add(mainMenu, BorderLayout.CENTER);


        //Ligne de code permetttant d'afficher correctement l'écran sous Mac et autre OS Unix.
        this.revalidate();
        this.repaint();
    }

    private JPanel createButton(JButton button){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        button.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        button.setBackground(Color.BLACK);


        button.setPreferredSize(buttonDimension);
        buttonPanel.add(button);
        return buttonPanel;
    }

    private void addMouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controller.mouseIsHovering(button);
            }

            public void mouseExited(MouseEvent e){
                controller.mouseIsOut(button);
            }
        });
    }
}
