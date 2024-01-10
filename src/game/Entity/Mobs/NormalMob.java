package game.Entity.Mobs;

import game.geometry.Coordinates;

import static game.ui.Style.*;


public class NormalMob extends Mob {
    public String entityWalk = "resources/assets/monstres/2/S_Walk.gif"; //initializeImage("resources/assets/monstres/2/S_Walk.gif");
    public String entityAttack = "resources/assets/monstres/2/S_Walk.gif"; //initializeImage("resources/assets/monstres/2/S_Walk.gif");
    public String entityDead = "resources/assets/monstres/2/S_Death.gif"; //initializeImage("resources/assets/monstres/2/S_Death.gif");
    public NormalMob(Coordinates coordinates){
        super("Entite normal", stringGras, 12, 60, coordinates, 50, "resources/assets/monstres/2/S_Walk.gif", "resources/assets/monstres/2/S_Attack.gif", "resources/assets/monstres/2/S_Death.gif", 0.10f);
    }

    public String getWalkGif(){return this.entityWalk;}
    public String getAttackGif(){return this.entityAttack;}
    public String getDeadGif(){return this.entityDead;}
}
