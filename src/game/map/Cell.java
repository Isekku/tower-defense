package game.map;

import game.mobs.Mobs;
import game.mobs.entities.Entity;
import game.mobs.towers.Tower;

class Cell {
    private Mobs mob;

    //Création d'une cellule vide;
    public Cell() {
        this.mob = null;
    }

    //Création d'une cellule ayant un mob (une entité ou une tour)
    public Cell(Mobs entity) {
        this.mob = entity;
    }

    public void setMob(Mobs mob) {
        this.mob = mob;
    }

    public Mobs getMob() {
        return this.mob;
    }


    public String toString() {
        if (this.mob == null) return ".  ";
        else if(this.mob instanceof Tower) return "T  ";
        else return "X  ";
    }

}