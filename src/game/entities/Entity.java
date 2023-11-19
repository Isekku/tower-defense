package game.entities;

public abstract class Entity {
    private int posX;
    private int posY;
    private int pv;
    private int pvMax;

    public Entity(int posX, int posY, int pv) {
        this.posX = posX;
        this.posY = posY;
        this.pv = pv;
        this.pvMax = pv;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    //Méthode permettant d'infligé des dégats à l'entité.
    public void takeDamage(int damage) {
        this.pv -= damage;
        if (this.pv < 0){
            destroy();
        }
    }

    //Méthode permettant de donner des points de vie à l'entité.
    public void heal(int value){
        if(pv + value >= pvMax) pv = pvMax;
        else pv += value;
    }

    public abstract void destroy();


}
