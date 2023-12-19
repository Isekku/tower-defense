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
}
