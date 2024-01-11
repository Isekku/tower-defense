package game.Entity.Mobs;

import game.Entity.Entity;
import game.Entity.towers.Tower;
import game.geometry.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Mob extends Entity{

    public int value;
    public boolean isKilling = false;
    public float speed;
    public Timer timer;
    public int delay;
    public Tower currentTower;

    public ArrayList<Tower> towerKilled = new ArrayList<>();
    public Mob(String nom, String couleur, int damage, int pv, Coordinates coordinates, int value, String entityWalk, String entityAttack, String entityDead, float speed, int delay){
        super(nom, couleur, damage, pv, "X", coordinates, entityWalk, entityAttack, entityDead);
        this.value = value;
        this.speed = speed;
        this.delay = delay;

        attack();
    }

    public void attack(){
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currentTower != null){
                    boolean dead = currentTower.takeDamage(getDamage());
                    if(dead){
                        towerKilled.add(currentTower);
                        timer.stop();
                    }
                }
            }
        });
    }

    public void destroy(){
        ;
    }

    public Mob clone(Coordinates coordinates){
        Mob mob = new Mob(this.nom, this.couleur, this.getDamage(), this.getPv(), coordinates, this.value, this.entityWalk, this.entityAttack, this.entityDead, this.speed, this.delay);
        return mob;
    }
}
