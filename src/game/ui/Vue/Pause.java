package game.ui.Vue;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import game.ui.Controller.PauseController;

import game.ui.Model.PlayingPanel;

import java.awt.event.ActionEvent; 

public class Pause extends JFrame implements State{

    //Tout attributs permettant d'instancier la classe :
    public static boolean isFirstTime = true;
    private static final Pause instance = new Pause();
    private PauseController controller;

    //Constructeur unique :
    public Pause() {
        controller = new PauseController(this);
        pausePanel = new JPanel(new GridLayout(2, 1));
        String imagePath = "/resources/buttons/";

    }

    //Les Panels et les Components besoins pour l'affichage :
    private JPanel pausePanel = new JPanel();

        private JPanel buttonPanel = new JPanel();
            JButton reprendre = new JButton("Reprendre");
            JButton retourMenuPrincipal = new JButton("Retour au menu principal");
            JButton sauvegarder = new JButton("Sauvegarder");
            JButton quitter = new JButton("Quitter");

    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        pausePanel.setLayout(new FlowLayout());
            buttonPanel.setLayout(new GridLayout(4, 1));
                buttonPanel.add(reprendre);
                addClickListener(reprendre, 1);
                buttonPanel.add(retourMenuPrincipal);
                addClickListener(retourMenuPrincipal, 2);
                buttonPanel.add(sauvegarder);
                addClickListener(sauvegarder, 3);
                buttonPanel.add(quitter);
                addClickListener(quitter, 4);

                reprendre.setPreferredSize(new Dimension(400, 100));
                retourMenuPrincipal.setPreferredSize(new Dimension(400, 100));
                sauvegarder.setPreferredSize(new Dimension(400, 100));
                quitter.setPreferredSize(new Dimension(400, 100));


        pausePanel.add(buttonPanel, BorderLayout.CENTER);
        pausePanel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));




    }

    public void exitState() {

    }

    public void addClickListener(JButton button, int value) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mouseClicked(value);
            }
        });
    }

    


    public static Pause getInstance(){
        return instance;
    }

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    public JPanel getView(){
        return this.pausePanel;
    }

    public void quitState() {

    }

    public void refresh() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                pausePanel.revalidate();
                pausePanel.repaint();
            }
        });
    }

    




}
