package game.Entity.Mobs;

import game.Entity.Entity;
import game.geometry.Coordinates;

import java.awt.*;

public class Mob extends Entity{

    public int value;
    public boolean isKilling = false;
    public Mob(String nom, String couleur, int damage, int pv, Coordinates coordinates, int value){
        super(nom, couleur, damage, pv, "X", coordinates);
        this.value = value;
    }

    public void destroy(){
        ;
    }

    public Mob clone(Coordinates coordinates){
        Mob mob = new Mob(this.nom, this.couleur, this.getDamage(), this.getPv(), coordinates, this.value);
        mob.setEntityGif(this.entityWalk.getImage(), this.entityAttack.getImage(), this.entityDead.getImage());
        return mob;
    }
}
