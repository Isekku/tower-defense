package game.entities;

public abstract class Entity {
    private int x;
    private int y;
    private int pv;
    private int pvMax;

    public Entity(int x, int y, int pv) {
        this.x = x;
        this.y = y;
        this.pv = pv;
        this.pvMax = pv;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void takeDamage(int damage) {
        this.pv -= damage;
        if (this.pv < 0){
            destroy();
        }
    }

    public abstract void destroy();


}
