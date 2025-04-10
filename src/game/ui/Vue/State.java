package game.ui.Vue;

import game.ui.Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public interface State {
    public void enterState();
    public void quitState();
    public JPanel getView();

    /**
     * Méthode permettant d'avoir un affichage correcte du Panel dans la fenêtre de l'utilisateur
     * */
    public void refresh();

    public boolean isFirstTime();
    public void notFirstTime();
}
