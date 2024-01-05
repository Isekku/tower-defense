package game.Entity;

import game.geometry.Coordinates;
import game.ui.Style;

import javax.swing.*;

public abstract class Entity {

    public Coordinates coordinates;
    public String printTerminal;
    public String nom;
    public String couleur;
    private int damage;
    private int pv;
    private int pvMax;
    private ImageIcon currentImage;
    private ImageIcon entityWalk;
    private ImageIcon entityAttack;
    private ImageIcon entityDead;

    /**
     * Constructeur permettant d'initialisé un mob. Il peut représenter une entité ou une tour.
     * */
    public Entity(String nom, String couleur, int damage, int pv, String printTerminal, Coordinates coordinates){
        this.printTerminal = couleur + printTerminal + Style.stringBase;
        this.nom = nom;
        this.couleur = couleur;
        this.damage = damage;
        this.pv = pv;
        this.pvMax = pv;
        this.coordinates = coordinates;
    }

    //Getter et Setter
    public int getDamage(){return this.damage;}
    public int getPv(){return this.pv;}
    public int getPvMax(){return this.pvMax;}
    public String getNom(){return this.nom;}

    /**
     * Méthode permettant d'infligé des dégats au mob courant.
     * */
    public boolean takeDamage(int damage) {
        this.pv -= damage;
        return this.pv <= 0;
    }

    /**
     * Méthode permettant d'infligé des dégats au mob donné en argument
     * */
    public boolean makeDamage(Entity mobs){
        return mobs.takeDamage(this.damage);
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

    public abstract Entity clone(Coordinates coordinates);
}
