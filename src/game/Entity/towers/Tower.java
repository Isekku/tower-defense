package game.Entity.towers;

import game.Entity.Entity;
import game.geometry.Coordinates;

public class Tower extends Entity implements Cloneable{
    private int cost;

    public Tower(String nom, String couleur, int damage, int pv, int pvMax, String printTerminal, int cost) {
        super(nom, couleur, damage, pv, pvMax, printTerminal);
        this.cost = cost;
    }

    public int getCost(){
        return this.cost;
    }

    public void destroy(){
        ;
    }
    
    public Tower clone(){
        return null;
    }
}
