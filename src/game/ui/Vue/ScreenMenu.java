package game.ui.Vue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

import game.ui.Controller.ScreenMenuController;
import game.Model;
import game.Save;
import game.ui.Style;

public class ScreenMenu extends JFrame implements State {
    //Tout attributs permettant d'instancier la classe :
    protected JPanel screenMenuPanel;
    public static boolean isFirstTime = true;
    private static final ScreenMenu instance = new ScreenMenu();
    private ScreenMenuController controller = new ScreenMenuController(this);

    //Constructeur unique :
    public ScreenMenu(){
        this.screenMenuPanel = new JPanel();
        screenMenuPanel.setLayout(new BorderLayout());
    }

    //Les Panels et les Components besoins pour l'affichage :
    private JPanel menuPanel = new JPanel();
        private JPanel menuTextPanel = new JPanel();
            private JLabel gameTitle = new JLabel("Plants Vs Zombie", JLabel.CENTER);
        private JPanel buttonContainerPanel = new JPanel();
            private JPanel buttonPanel = new JPanel();
                private JButton playButton = new JButton("Jouer");
                private JButton optionButton = new JButton("Chargez une partie");
                private JButton quitButton = new JButton("Quitter");

    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        menuPanel.setLayout(new GridLayout(2, 1));
            menuTextPanel.setLayout(new BorderLayout());
                gameTitle.setFont(Style.titleFont);
                menuTextPanel.add(gameTitle);

                menuTextPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
                menuTextPanel.add(gameTitle, BorderLayout.SOUTH);


                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                    addClickListener(playButton, controller.PLAY_GAME);
                    stylishButton(playButton);
                    buttonPanel.add(playButton);

                    buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                    addClickListener(optionButton, controller.OPTION_GAME);
                    stylishButton(optionButton);
                    buttonPanel.add(optionButton);

                    buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                    addClickListener(quitButton, controller.LEAVE_GAME);
                    stylishButton(quitButton);
                    buttonPanel.add(quitButton);

                    buttonPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

            buttonContainerPanel.setLayout(new BorderLayout());
            buttonContainerPanel.add(buttonPanel, BorderLayout.CENTER);

            menuPanel.add(menuTextPanel);
            menuPanel.add(buttonContainerPanel);

        screenMenuPanel.add(menuPanel, BorderLayout.CENTER);

        checkSave();

        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }
    public void checkSave(){
        if (!Save.isSaveExist()) {
            optionButton.setEnabled(false);
            System.out.println("Aucune sauvegarde trouvée");
        }
        else {
            System.out.println("Sauvegarde trouvée");
            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    loadSave();
                }
            });
        }

    }
    public void loadSave(){
        Model save = Save.load();
        if (save == null) {
            JOptionPane.showMessageDialog(null, "Aucune sauvegarde trouvée", "Erreur", JOptionPane.ERROR_MESSAGE);
            optionButton.setEnabled(false);
        }

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
        stylishButton(playButton);
        stylishButton(optionButton);
        stylishButton(quitButton);
    }
    public boolean isFirstTime(){
        return isFirstTime;
    }
    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    //Méthode propre à la construction de Component plus spécifiques :
    private void stylishButton(JButton button){
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(Style.buttonDimension);
        button.setFont(Style.buttonFont);
        Style.stylishButton(button);
    }

    //Méthodes permettant d'attribuer les méthodes en fonction d'action produit sur le bouton :
    private void addClickListener(JButton button, int value){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.mouseClicked(value);
            }
        });
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

    //Méthodes nécessaire pour l'accessibilité externe de la classe :
    public static ScreenMenu getInstance(){
        return instance;
    }
    public JPanel getView(){
        return this.screenMenuPanel;
    }
}
