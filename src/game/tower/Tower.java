package game.tower;

public abstract class Tower {
    private int posX;
    private int posY;
    private int range;
    private int cost;    
    private int damage;
    private int pv;
    private int pvMax;

    public Tower(int posX, int posY, int range, int cost, int damage, int pv) {
        this.posX = posX;
        this.posY = posY;
        this.range = range;
        this.cost = cost;
        this.damage = damage;
        this.pv = pv;
        this.pvMax = pv;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    

}
