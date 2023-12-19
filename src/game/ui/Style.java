package game.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Style {
    public static Font titleFont = new Font("Comics Sans MS", Font.BOLD, 60);
    public static Font buttonFont = new Font("Comics Sans MS", Font.BOLD, 20);
    public static Font descriptionFont = new Font("Comics Sans MS", Font.BOLD, 40);
    public static Font littleButtonFont = new Font("Comics Sans MS", Font.BOLD, 12);
    public static Dimension buttonDimension = new Dimension(300, 70);
    public static Dimension littleButtonDimension = new Dimension(200, 50);

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
