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

    public static void buttonIsHovered(JButton button){
        button.setForeground(Color.GRAY);
        Border line = new LineBorder(Color.GRAY,5, true);
        Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));
        button.setBorder(compound);
    }

    public static void buttonIsNormal(JButton button){
        button.setForeground(Color.BLACK);
        Border line = new LineBorder(Color.BLACK,5, true);
        Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));
        button.setBorder(compound);
    }

    public static void stylishButton(JButton button){
        button.setForeground(Color.BLACK);

        Border line = new LineBorder(Color.BLACK,5, true);
        Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));

        button.setBorder(compound);
    }
}
