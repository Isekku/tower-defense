package game.Entity.towers;

import game.Entity.Entity;
import game.Entity.Projectile;
import game.geometry.Coordinates;

import java.awt.*;

public class Tower extends Entity{
    private int cost;
    public boolean canShoot = true;
    public String currentAnimation;
    public String archerHandle;
    public String archerAttack;

    public Tower(String nom, String couleur, int damage, int pv, String printTerminal, int cost, Coordinates coordinates, String entityWalk, String entityAttack, String entityDead) {
        super(nom, couleur, damage, pv, printTerminal, coordinates, entityWalk, entityAttack, entityDead);
        this.cost = cost;
        this.archerHandle = entityAttack;
        this.archerAttack = entityDead;
        this.currentAnimation = this.archerHandle;
    }

    public int getCost(){
        return this.cost;
    }

    public Projectile shoot(Tower tower, int width, int height){
        return new Projectile(this.couleur, this.getDamage(), tower, width, height);
    }

    public void destroy(){
        ;
    }
    
    public Tower clone(Coordinates coordinates){
        Tower tower =  new Tower(this.nom, this.couleur, this.getDamage(), this.getPv(), this.printTerminal, this.cost, coordinates, entityWalk, entityAttack, entityDead);
        //tower.setEntityGif(tower.entityWalk, tower.entityAttack, tower.entityDead);
        return tower;
    }
}
