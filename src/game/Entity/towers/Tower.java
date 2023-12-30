package game.Entity.towers;

import game.Entity.Entity;
import game.Entity.Projectile;
import game.geometry.Coordinates;

public class Tower extends Entity implements Cloneable{
    private int cost;
    public boolean canShoot = true;

    public Tower(String nom, String couleur, int damage, int pv, int pvMax, String printTerminal, int cost) {
        super(nom, couleur, damage, pv, pvMax, printTerminal);
        this.cost = cost;
    }

    public int getCost(){
        return this.cost;
    }

    public Projectile shoot(Tower tower){
        return new Projectile(this.couleur, this.getDamage(), tower);
    }

    public void destroy(){
        ;
    }
    
    public Tower clone(){
        return new Tower(this.nom, this.couleur, this.getDamage(), this.getPv(), this.getPvMax(), this.printTerminal, this.cost);
    }
}
