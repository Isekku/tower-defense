package game.Entity.towers;

import game.Entity.Entity;
import game.Entity.Projectile;
import game.geometry.Coordinates;
import game.map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tower extends Entity{
    private int cost;
    public boolean canShoot = true;
    public String currentAnimation;
    public String archerHandle;
    public String archerAttack;

    public int delay;
    public Timer timer;

    public ArrayList<Projectile> projectileShooted = new ArrayList<>();

    public Tower(String nom, String couleur, int damage, int pv, String printTerminal, int cost, Coordinates coordinates, String entityWalk, String entityAttack, String entityDead, int delay) {
        super(nom, couleur, damage, pv, printTerminal, coordinates, entityWalk, entityAttack, entityDead);
        this.cost = cost;
        this.archerHandle = entityAttack;
        this.archerAttack = entityDead;
        this.currentAnimation = this.archerHandle;
        this.delay = delay;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("shooté");
                projectileShooted.add(shoot(Map.getInstance().getWidth(), Map.getInstance().getHeight()));
            }
        });
    }

    public int getCost(){
        return this.cost;
    }

    public Projectile shoot(int width, int height){
        return new Projectile(this.couleur, this.getDamage(), this, width, height);
    }

    public void destroy(){
        ;
    }
    
    public Tower clone(Coordinates coordinates){
        Tower tower =  new Tower(this.nom, this.couleur, this.getDamage(), this.getPv(), this.printTerminal, this.cost, coordinates, entityWalk, entityAttack, entityDead, delay);
        //tower.setEntityGif(tower.entityWalk, tower.entityAttack, tower.entityDead);
        return tower;
    }
}
