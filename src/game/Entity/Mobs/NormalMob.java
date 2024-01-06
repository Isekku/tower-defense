package game.Entity.Mobs;

import game.geometry.Coordinates;

import java.awt.*;

import static game.ui.Style.*;


public class NormalMob extends Mob{
    public Image entityWalk = initializeImage("resources/assets/monstres/1/S_Walk.gif");
    public Image entityAttack = initializeImage("resources/assets/monstres/1/S_Walk.gif");
    public Image entityDead = initializeImage("resources/assets/monstres/1/S_Death.gif");
    public NormalMob(Coordinates coordinates){
    super("Entite normal", stringGras, 12, 60, coordinates, 10);
    }
}
