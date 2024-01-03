package game.Entity;

import game.Entity.towers.Tower;
import game.geometry.Coordinates;
import game.map.Map;

public class Projectile extends Entity{
    public int effect = -1;
    public Tower towerParent;
    public Map projectileMap;

    public Projectile(String couleur, int damage, Tower towerParent, int width, int height){
        super("Projectile", couleur, damage, 0, ".", towerParent.coordinates.clone());
        projectileMap = new Map(width, height);
        projectileMap.setEntity(this.coordinates, this);
        this.towerParent = towerParent;
        this.projectileMap = new Map(width, height);
    }

    public Projectile(String couleur, int damage, Tower towerParent, int width, int height, int effect){
        this(couleur, damage, towerParent, width, height);
        this.effect = effect;
    }

    public void moveRight(Coordinates c){
        c.moveRight();
    }

    public void destroy(){}

    public Projectile clone(Coordinates coordinates){
        return null;
    }
}
