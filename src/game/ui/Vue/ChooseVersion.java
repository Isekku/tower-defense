package game.ui.Vue;

import game.ui.Controller.ChooseVersionController;
import game.ui.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseVersion extends JFrame implements State {
    //Tout attributs permettant d'instancier la classe :
    protected JPanel chooseVersionPanel;
    public static boolean isFirstTime = true;
    private static final ChooseVersion instance = new ChooseVersion();
    private ChooseVersionController controller;

    //Constructeur unique :
    public ChooseVersion(){
        controller = new ChooseVersionController(this);
        chooseVersionPanel = new JPanel();
        chooseVersionPanel.setLayout(new BorderLayout());
    }
    //Les Panels et les Components besoins pour l'affichage :
        JPanel chooseTextPanel = new JPanel();
            JLabel chooseVersionText = new JLabel();
        JPanel buttonPanel = new JPanel();
            JPanel chooseVersionButtonPanel = new JPanel();
                JButton normalButton = new JButton();
                JButton marathonButton = new JButton();
            JPanel goBackButtonPanel = new JPanel();
                JButton goBackButton = new JButton();

    //Méthodes nécessaires pour la construction de la vue :
    public void enterState(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        chooseVersionPanel.setLayout(new GridLayout(2, 1));

        chooseTextPanel.setLayout(new BorderLayout());
            chooseVersionText.setText("Choisissez un mode de jeu :");
            chooseVersionText.setFont(new Font("Comics Sans MS", Font.BOLD, 50));
            chooseVersionText.setHorizontalAlignment(SwingConstants.CENTER);
            chooseTextPanel.add(chooseVersionText, BorderLayout.SOUTH);
            chooseTextPanel.setBorder(new EmptyBorder(0, 0, 50, 0));

        buttonPanel.setLayout(new BorderLayout());
            chooseVersionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
                stylishButton(normalButton, "Mode Normale", false);
                addClickListener(normalButton, controller.NORMAL_MODE);

                stylishButton(marathonButton, "Mode Marathon", false);
                addClickListener(marathonButton, controller.MARATHON_MODE);

                chooseVersionButtonPanel.add(normalButton);
                chooseVersionButtonPanel.add(marathonButton);

            goBackButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                stylishButton(goBackButton, "<- Retour en arrière", true);
                addClickListener(goBackButton, controller.GO_BACK);

                goBackButtonPanel.add(goBackButton);

            buttonPanel.add(chooseVersionButtonPanel, BorderLayout.CENTER);
            buttonPanel.add(goBackButtonPanel, BorderLayout.SOUTH);

        chooseVersionPanel.add(chooseTextPanel);
        chooseVersionPanel.add(buttonPanel);

        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }
    public void refresh(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chooseVersionPanel.revalidate();
                chooseVersionPanel.repaint();
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

    //Méthodes propre à la construction de Component plus spécifique :
    public void stylishButton(JButton button, String text, boolean isLittle){
        button.setText(text);
        if(isLittle){
            button.setFont(Style.littleButtonFont);
            button.setPreferredSize(Style.littleButtonDimension);
        }
        else{
            button.setFont(Style.buttonFont);
            button.setPreferredSize(Style.buttonDimension);
        }
        Style.stylishButton(button);
    }

    //Méthodes permettant d'attribuer les méthodes en fonction d'action produit sur le bouton :
    public void addClickListener(JButton button, int value) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mouseClicked(value);
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controller.mouseIsHovering(button);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                controller.mouseIsOut(button);
            }
        });
    }

    //Méthodes nécessaire pour l'accessibilité externe de la classe :
    public static ChooseVersion getInstance(){
        return instance;
    }
    public JPanel getView(){
        return chooseVersionPanel;
    }
}
