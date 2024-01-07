package game.Entity.Mobs;

import game.geometry.Coordinates;
import game.ui.Model.AnimatedGif;

import java.awt.*;

import static game.ui.Style.*;


public class NormalMob extends Mob implements AnimatedGif {
    public String entityWalk = "resources/assets/monstres/2/S_Walk.gif"; //initializeImage("resources/assets/monstres/2/S_Walk.gif");
    public String entityAttack = "resources/assets/monstres/2/S_Walk.gif"; //initializeImage("resources/assets/monstres/2/S_Walk.gif");
    public String entityDead = "resources/assets/monstres/2/S_Death.gif"; //initializeImage("resources/assets/monstres/2/S_Death.gif");
    public NormalMob(Coordinates coordinates){
        super("Entite normal", stringGras, 12, 60, coordinates, 10, "resources/assets/monstres/2/S_Walk.gif", "resources/assets/monstres/2/S_Walk.gif", "resources/assets/monstres/2/S_Death.gif");
    }

    public String getWalkGif(){return this.entityWalk;}
    public String getAttackGif(){return this.entityAttack;}
    public String getDeadGif(){return this.entityDead;}
}
