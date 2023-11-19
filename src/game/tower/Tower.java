package game.tower;

public abstract class Tower {
    private int x;
    private int y;
    private int range;
    private int cost;    
    private int damage;
    private int pv;
    private int pvMax;

    public Tower(int x, int y, int range, int cost, int damage, int pv) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.cost = cost;
        this.damage = damage;
        this.pv = pv;
        this.pvMax = pv;
    }
    
}
