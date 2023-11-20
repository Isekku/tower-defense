package game.map;

import game.entities.Entity;
import game.tower.Tower;

class Cell {
    private Entity entity;
    private Tower tower;

    //Création d'une cellule vide;
    public Cell() {
        this.entity = null;
        this.tower = null;
    }

    //Création d'une cellule ayant un entité
    public Cell(Entity entity) {
        this.entity = entity;
    }

    //Création d'une cellule ayant une tour
    public Cell(Tower tower) {
        this.tower = tower;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public Tower getTower() {
        return this.tower;
    }

    public String toString() {
        if (entity == null && tower == null) return ".  ";
        else if (entity == null) return "X  ";
        else return "T  ";
    }

}