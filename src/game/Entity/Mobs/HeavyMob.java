package game.Entity.Mobs;

import game.geometry.Coordinates;

import java.awt.*;

import static game.ui.Style.*;

public class HeavyMob extends Mob{
    public Image entityWalk = initializeImage("resources/assets/monstres/3/S_Walk.gif");
    public java.awt.Image entityAttack = initializeImage("resources/assets/monstres/3/S_Walk.gif");
    public Image entityDead = initializeImage("resources/assets/monstres/3/S_Death.gif");
    public HeavyMob(Coordinates coordinates){
        super("Mob Lourd", stringCouleurRouge, 16, 80, coordinates, 15);
    }
}
