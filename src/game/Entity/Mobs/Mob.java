package game.Entity.Mobs;

import game.Entity.Entity;
import game.geometry.Coordinates;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Mob extends Entity{

    public int value;
    public boolean isKilling = false;
    public Mob(String nom, String couleur, int damage, int pv, Coordinates coordinates, int value){
        super(nom, couleur, damage, pv, "X", coordinates);
        this.value = value;
    }

    public void setEntityImage(ImageIcon entityImage){
        super.setEntityImage(entityImage);
    }


    public void destroy(){
        ;
    }

    public Mob clone(Coordinates coordinates){
        return new Mob(this.nom, this.couleur, this.getDamage(), this.getPv(), coordinates, this.value);
    }
}
