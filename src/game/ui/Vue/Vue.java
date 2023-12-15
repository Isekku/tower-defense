package game.ui.Vue;

import javax.swing.*;

public class Vue {
    private JFrame view;

    public Vue(JFrame view){
        this.view = view;
    }

    public void affiche(){
        view.setVisible(true);
    }

    public void setView(JFrame view){
        this.view = view;
    }
}
