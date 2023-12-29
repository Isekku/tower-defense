package game.Entity.Mobs;

import game.Entity.Entity;

public class Mob extends Entity{

    public Mob(String nom, String couleur, int damage, int pv, int pvMax){
        super(nom, couleur, damage, pv, pvMax, "X");
    }

    public void destroy(){
        ;
    }


}
