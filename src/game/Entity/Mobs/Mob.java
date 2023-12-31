package game.Entity.Mobs;

import game.Entity.Entity;
import game.geometry.Coordinates;

public class Mob extends Entity{

    public Mob(String nom, String couleur, int damage, int pv, int pvMax, Coordinates coordinates){
        super(nom, couleur, damage, pv, pvMax, "X", coordinates);
    }

    public void destroy(){
        ;
    }

    public Mob clone(Coordinates coordinates){
        return null;
    }
}
