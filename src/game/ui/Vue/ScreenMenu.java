package game.ui.Vue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

import game.ui.Controller.ScreenMenuController;
import game.ui.Style;

public class ScreenMenu extends JFrame implements State {
    JPanel screenMenuPanel;
    public static boolean isFirstTime = true;
    private static final ScreenMenu instance = new ScreenMenu();
    ScreenMenuController controller = new ScreenMenuController(this);

    JPanel menuPanel = new JPanel();
        JPanel menuTextPanel = new JPanel();
            JLabel gameTitle = new JLabel("Plants Vs Zombie", JLabel.CENTER);
        JPanel buttonContainerPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
                JButton playButton = new JButton("Jouer");
                JButton optionButton = new JButton("Option");
                JButton quitButton = new JButton("Quitter");

    public ScreenMenu(){
        this.screenMenuPanel = new JPanel();
        screenMenuPanel.setLayout(new BorderLayout());
    }

    public void enterState() {
        menuPanel.setLayout(new GridLayout(2, 1));
            menuTextPanel.setLayout(new BorderLayout());
                gameTitle.setFont(Style.titleFont);
                menuTextPanel.add(gameTitle);

                menuTextPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
                menuTextPanel.add(gameTitle, BorderLayout.SOUTH);


                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                    addMouseListener(playButton);
                    stylishButton(playButton);
                    playButton.addActionListener((event) -> controller.mouseClicked(controller.PLAY_GAME));
                    buttonPanel.add(playButton);

                    buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                    addMouseListener(optionButton);
                    stylishButton(optionButton);
                    optionButton.addActionListener((event) -> controller.mouseClicked(controller.OPTION_GAME));
                    buttonPanel.add(optionButton);

                    buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                    addMouseListener(quitButton);
                    stylishButton(quitButton);
                    quitButton.addActionListener((event) -> controller.mouseClicked(controller.LEAVE_GAME));
                    buttonPanel.add(quitButton);

                    buttonPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

            buttonContainerPanel.setLayout(new BorderLayout());
            buttonContainerPanel.add(buttonPanel, BorderLayout.CENTER);

            menuPanel.add(menuTextPanel);
            menuPanel.add(buttonContainerPanel);

        screenMenuPanel.add(menuPanel, BorderLayout.CENTER);

        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }

    public void refresh(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                screenMenuPanel.revalidate();
                screenMenuPanel.repaint();
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

    public static ScreenMenu getInstance(){
        return instance;
    }

    public JPanel getView(){
        return this.screenMenuPanel;
    }

    private void stylishButton(JButton button){
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(Style.buttonDimension);
        button.setFont(Style.buttonFont);
        Style.stylishButton(button);
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
