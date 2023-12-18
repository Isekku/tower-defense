package game.ui;

import javax.swing.*;

public interface State {
    public void enterState();
    public void quitState();
    public JPanel getView();
}
