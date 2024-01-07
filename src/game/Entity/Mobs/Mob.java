package game.Entity.Mobs;

import game.Entity.Entity;
import game.geometry.Coordinates;

import java.awt.*;

public class Mob extends Entity{

    public int value;
    public boolean isKilling = false;
    public float speed;
    public Mob(String nom, String couleur, int damage, int pv, Coordinates coordinates, int value, String entityWalk, String entityAttack, String entityDead, float speed){
        super(nom, couleur, damage, pv, "X", coordinates, entityWalk, entityAttack, entityDead);
        this.value = value;
        this.speed = speed;
    }

    public void destroy(){
        ;
    }

    public Mob clone(Coordinates coordinates){
        Mob mob = new Mob(this.nom, this.couleur, this.getDamage(), this.getPv(), coordinates, this.value, this.entityWalk, this.entityAttack, this.entityDead, this.speed);
        return mob;
    }
}
