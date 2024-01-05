package game.map;

import game.Entity.Entity;
import game.Entity.towers.Tower;

public class Cell {
    private Entity entity;

    //Création d'une cellule vide;
    public Cell() {
        this.entity = null;
    }

    //Création d'une cellule ayant un mob (une entité ou une tour)
    public Cell(Entity entity) {
        this.entity = entity;
    }

    public void setEntity(Entity mob) {
        this.entity = mob;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public String toString() {
        //Ne modifie pas pour l'affichage terminale s'il te plaît
        if (this.entity == null) return "   ";
        //else if(this.mob instanceof Tower) return "T  ";
        else return this.entity.printTerminal + "  ";
    }

}