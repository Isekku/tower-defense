package game.Entity.Mobs;

import game.Entity.Entity;
import game.geometry.Coordinates;
import game.ui.Model.AnimatedGif;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static game.ui.Style.*;

public class WeakMob extends Mob {

    public static String entityWalk = "resources/assets/monstres/1/S_Walk.gif"; //initializeImage("resources/assets/monstres/1/S_Walk.gif");
    public static String entityAttack = "resources/assets/monstres/1/S_Walk.gif"; //initializeImage("resources/assets/monstres/1/S_Walk.gif");
    public static Image entttt = initializeImage("resources/assets/monstres/1/S_Death.gif");
    public static String entityDead = "resources/assets/monstres/1/S_Death.gif"; //initializeImage("resources/assets/monstres/1/S_Death.gif");

    public  WeakMob(Coordinates coordinates){
        super("Mob Faible", stringBase, 1, 40, coordinates, 25, "resources/assets/monstres/1/S_Walk.gif", "resources/assets/monstres/1/S_Walk.gif", "resources/assets/monstres/1/S_Death.gif", 0.05f);
    }
}
