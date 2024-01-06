package game.Entity.Mobs;

import game.geometry.Coordinates;

import javax.swing.*;
import java.awt.*;

import static game.ui.Style.*;

public class WeakMob extends Mob{

    public Image entityWalk = initializeImage("resources/assets/monstres/1/S_Walk.gif");
    public Image entityAttack = initializeImage("resources/assets/monstres/1/S_Walk.gif");
    public Image entityDead = initializeImage("resources/assets/monstres/1/S_Death.gif");

    public  WeakMob(Coordinates coordinates){
        super("Mob Faible", stringBase, 8, 40, coordinates, 5);
    }
}
