package game.ui;

import game.geometry.Coordinates;

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
    public static Border line = new LineBorder(Color.BLACK,5, true);
    public static Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));
    public static String stringGras = "\u001B[1m";
    public static String stringBase = "\u001B[0m";
    public static String stringCouleurCyan = "\u001B[36m";
    public static String stringCouleurRouge = "\u001B[31m";
    public static String stringCouleurPourpre = "\u001B[35m";
    public static String stringCouleurVert = "\u001B[32m";
    public static String stringCouleurJaune= "\u001B[33m";
    public static final String stringErrorMessage = stringGras + stringCouleurRouge + "La valeur entré est incorrecte !";
    public static final String stringNotEnoughMoney = stringGras + stringCouleurRouge + "Vous n'avez pas assez de monnaie !";
    public static final String stringTowerAlreadyHere = stringGras + stringCouleurRouge + "Une tour est déjà placé ici !";


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
        button.setOpaque(false);

        Border line = new LineBorder(Color.BLACK,5, true);
        Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));

        button.setBorder(compound);
    }
}
