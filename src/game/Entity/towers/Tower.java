package game.Entity.towers;

import game.Entity.Entity;
import game.geometry.Coordinates;

public class Tower extends Entity{
    private int cost;

    public Tower(int posX, int posY, int damage, int pv, int pvMax, String printTerminal, int cost) {
        super(posX, posY, damage, pv, pvMax, "T");
        this.cost = cost;
    }

    public Tower(Coordinates c, int damage, int pv, int pvMax, int range, int cost) {
        super(c, damage, pv, pvMax);
        this.cost = cost;
    }

    public void destroy(){
        ;
    }
    

}
