package game.ui.Vue;

import game.ui.Controller.ChooseVersionController;
import game.ui.State;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChooseVersion extends JFrame implements State {
    protected JPanel chooseVersionPanel;
    Dimension buttonDimension = new Dimension(200, 70);

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
            chooseVersionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 160, 0));
                normalButton.setText("Mode Normale");
                normalButton.setPreferredSize(buttonDimension);
                normalButton.setFont(new Font("Comics Sans MS", Font.BOLD, 16));
                normalButton.addActionListener((event) -> controller.mouseClicked(controller.NORMAL_MODE));

                marathonButton.setText("Mode Marathon");
                marathonButton.setPreferredSize(buttonDimension);
                marathonButton.setFont(new Font("Comics Sans MS", Font.BOLD, 16));
                marathonButton.addActionListener((event) -> controller.mouseClicked(controller.MARATHON_MODE));

                chooseVersionButtonPanel.add(normalButton);
                chooseVersionButtonPanel.add(marathonButton);

            goBackButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                goBackButton.setText("<- Retour en arrière");
                goBackButton.setFont(new Font("Comics Sans MS", Font.BOLD, 12));
                goBackButton.setPreferredSize(new Dimension(200, 50));
                goBackButton.addActionListener((event) -> controller.mouseClicked(controller.GO_BACK));

                goBackButtonPanel.add(goBackButton);

            buttonPanel.add(chooseVersionButtonPanel, BorderLayout.CENTER);
            buttonPanel.add(goBackButtonPanel, BorderLayout.SOUTH);

        chooseVersionPanel.add(chooseTextPanel);
        chooseVersionPanel.add(buttonPanel);
    }

    public void quitState(){}

    public static ChooseVersion getInstance(){
        return instance;
    }

    public JPanel getView(){
        return chooseVersionPanel;
    }
}
