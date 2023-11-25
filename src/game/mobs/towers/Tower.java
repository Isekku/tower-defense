package game.mobs.towers;

import game.mobs.Mobs;

public class Tower extends Mobs{
    private int range;
    private int cost;

    public Tower(int posX, int posY, int damage, int pv, int pvMax, int range, int cost) {
        super(posX, posY, damage, pv, pvMax);
        this.range = range;
        this.cost = cost;
    }

    public void destroy(){
        ;
    }
    

}
