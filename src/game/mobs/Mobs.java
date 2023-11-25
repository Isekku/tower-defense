package game.mobs;

import game.geometry.Coordinates;

public abstract class Mobs {

    private Coordinates coordinates;
    private int damage;
    private int pv;
    private int pvMax;

    /**
     * Constructeur permettant d'initialisé un mob. Il peut représenter une entité ou une tour.
     * */
    public Mobs(int posX, int posY, int damage, int pv, int pvMax){
        this.coordinates = new Coordinates(posX, posY);
        this.damage = damage;
        this.pv = pv;
        this.pvMax = pvMax;
    }
    /**
     * Constructeur permettant d'initialisé un mob. Il peut représenter une entité ou une tour.
     * */
    public Mobs(Coordinates c, int damage, int pv, int pvMax){
        this.coordinates = c;
        this.damage = damage;
        this.pv = pv;
        this.pvMax = pvMax;
    }

    //Getter et Setter
    public int getX(){return coordinates.getX();}
    public int getY(){return coordinates.getY();}
    public int getDamage(){return this.damage;}
    public int getPv(){return this.pv;}
    public int getPvMax(){return this.pvMax;}

    /**
     * Méthode permettant d'infligé des dégats au mob courant.
     * */
    public void takeDamage(int damage) {
        this.pv -= damage;
        if (this.pv < 0){
            destroy();
        }
    }

    /**
     * Méthode permettant d'infligé des dégats au mob donné en argument
     * */
    public void makeDamage(Mobs mobs){
        mobs.takeDamage(this.damage);
    }

    /**
     * Méthode permettant de donner des points de vie à l'entité.
     * */
    public void heal(int value){
        if(this.pv + value >= this.pvMax) this.pv = this.pvMax;
        else this.pv += value;
    }

    /**
     * Méthode abstraite permettant de soit tuer le mob ou de le mettre dans un nouvel état.
     * */
    public abstract void destroy();


}
