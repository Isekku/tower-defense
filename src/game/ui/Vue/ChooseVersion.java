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
    protected JPanel chooseVersionPanel;
    public static boolean isFirstTime = true;

        protected JPanel chooseTextPanel = new JPanel();
            protected JLabel chooseVersionText = new JLabel();
        protected JPanel buttonPanel = new JPanel();
            protected JPanel chooseVersionButtonPanel = new JPanel();
                protected JButton normalButton = new JButton();
                protected JButton marathonButton = new JButton();
            protected JPanel goBackButtonPanel = new JPanel();
                protected JButton goBackButton = new JButton();


    private ChooseVersionController controller;
    private static final ChooseVersion instance = new ChooseVersion();

    public ChooseVersion(){
        controller = new ChooseVersionController(this);
        chooseVersionPanel = new JPanel();
        chooseVersionPanel.setLayout(new BorderLayout());
    }

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

    public void quitState(){
    }

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    public static ChooseVersion getInstance(){
        return instance;
    }

    public JPanel getView(){
        return chooseVersionPanel;
    }
}
