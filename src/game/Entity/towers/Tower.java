package game.Entity.towers;

import game.Entity.Entity;
import game.Entity.Projectile;
import game.geometry.Coordinates;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Tower extends Entity{
    private int cost;
    public boolean canShoot = true;

    public Tower(String nom, String couleur, int damage, int pv, String printTerminal, int cost, Coordinates coordinates) {
        super(nom, couleur, damage, pv, printTerminal, coordinates);
        this.cost = cost;
    }

    public int getCost(){
        return this.cost;
    }

    public Projectile shoot(Tower tower, int width, int height){
        return new Projectile(this.couleur, this.getDamage(), tower, width, height);
    }

    public void destroy(){
        ;
    }
    
    public Tower clone(Coordinates coordinates){
        return new Tower(this.nom, this.couleur, this.getDamage(), this.getPv(), this.printTerminal, this.cost, coordinates);
    }
}
