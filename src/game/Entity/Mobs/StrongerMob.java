package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.ui.Model.AnimatedGif;

import java.awt.*;

import static game.ui.Style.*;

public class StrongerMob extends Mob implements AnimatedGif {
    public static String entityWalk = "resources/assets/monstres/4/S_Walk.gif"; //initializeImage("resources/assets/monstres/4/S_Walk.gif");
    public static String entityAttack = "resources/assets/monstres/4/S_Walk.gif"; //initializeImage("resources/assets/monstres/4/S_Walk.gif");
    public static String entityDead = "resources/assets/monstres/4/S_Death.gif"; //initializeImage("resources/assets/monstres/4/S_Death.gif");
    public StrongerMob(Coordinates coordinates){
        super("Mob Fort", (stringGras + stringCouleurRouge), 20, 100, coordinates, 20, "resources/assets/monstres/4/S_Walk.gif", "resources/assets/monstres/4/S_Walk.gif", "resources/assets/monstres/4/S_Death.gif", 1.5f);
    }

    public String getWalkGif(){return this.entityWalk;}
    public String getAttackGif(){return this.entityAttack;}
    public String getDeadGif(){return this.entityDead;}
}
