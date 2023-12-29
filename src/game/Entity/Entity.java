package game.Entity;

import game.geometry.Coordinates;
import game.ui.Style;

public abstract class Entity {

    public String printTerminal;
    public String nom;
    public String couleur;
    private int damage;
    private int pv;
    private int pvMax;

    /**
     * Constructeur permettant d'initialisé un mob. Il peut représenter une entité ou une tour.
     * */
    public Entity(String nom, String couleur, int damage, int pv, int pvMax, String printTerminal){
        this.printTerminal = couleur + printTerminal + Style.stringBase;
        this.nom = nom;
        this.couleur = couleur;
        this.damage = damage;
        this.pv = pv;
        this.pvMax = pvMax;
    }

    //Getter et Setter
    public int getDamage(){return this.damage;}
    public int getPv(){return this.pv;}
    public int getPvMax(){return this.pvMax;}
    public String getNom(){return this.nom;}

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
    public void makeDamage(Entity mobs){
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
