package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.Model.AnimatedGif;

import static game.ui.Style.*;

public class HeavyMob extends Mob implements AnimatedGif {
    public static String entityWalk = "resources/assets/monstres/3/S_Walk.gif"; //nitializeImage("resources/assets/monstres/3/S_Walk.gif");
    public static String entityAttack = "resources/assets/monstres/3/S_Walk.gif"; // initializeImage("resources/assets/monstres/3/S_Walk.gif");
    public static String entityDead = "resources/assets/monstres/3/S_Death.gif"; //initializeImage("resources/assets/monstres/3/S_Death.gif");
    public HeavyMob(Coordinates coordinates){
        super("Mob Lourd", stringCouleurRouge, 16, 160, coordinates, 75, "resources/assets/monstres/3/S_Walk.gif", "resources/assets/monstres/3/S_Walk.gif", "resources/assets/monstres/3/S_Death.gif", 0.15f);
    }

    public String getWalkGif(){return this.entityWalk;}
    public String getAttackGif(){return this.entityAttack;}
    public String getDeadGif(){return this.entityDead;}
}
