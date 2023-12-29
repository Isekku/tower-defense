package game.map;

import game.Entity.Entity;
import game.Entity.towers.Tower;

public class Cell {
    private Entity mob;

    //Création d'une cellule vide;
    public Cell() {
        this.mob = null;
    }

    //Création d'une cellule ayant un mob (une entité ou une tour)
    public Cell(Entity entity) {
        this.mob = entity;
    }

    public void setMob(Entity mob) {
        this.mob = mob;
    }

    public Entity getMob() {
        return this.mob;
    }


    public String toString() {
        if (this.mob == null) return ".  ";
        //else if(this.mob instanceof Tower) return "T  ";
        else return this.mob.printTerminal + "  ";
    }

}