package game.Entity;

import game.Entity.towers.Tower;
import game.geometry.Coordinates;

public class Projectile extends Entity{
    public int effect = -1;
    public Tower towerParent;

    public Projectile(String couleur, int damage, Tower towerParent){
        super("Projectile", couleur, damage, 0, 0, ".");
        this.towerParent = towerParent;
    }

    public Projectile(String couleur, int damage, Tower towerParent, int effect){
        this(couleur, damage, towerParent);
        this.effect = effect;
    }

    public void moveRight(Coordinates c){
        c.moveRight();
    }

    public void destroy(){}
}
