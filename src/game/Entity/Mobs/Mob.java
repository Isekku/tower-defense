package game.Entity.Mobs;

import game.Entity.Entity;
import game.geometry.Coordinates;

public class Mob extends Entity{

    public int value;
    public Mob(String nom, String couleur, int damage, int pv, int pvMax, Coordinates coordinates, int value){
        super(nom, couleur, damage, pv, pvMax, "X", coordinates);
        this.value = value;
    }

    public void destroy(){
        ;
    }

    public Mob clone(Coordinates coordinates){
        return new Mob(this.nom, this.couleur, this.getDamage(), this.getPv(), this.getPvMax(), coordinates, this.value);
    }
}
