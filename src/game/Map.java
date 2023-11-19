package game;

import game.entities.Entity;
import game.tower.Tower;

public class Map {
    private int longueur;
    private int hauteur;
    private Cell[][] map;

    /*
    Class Cell:

    - Une classe interne de Map permettant de configurer une cellule qui sera utilisé pour la map.
    - Elle peut contenir :
        - Une entité
        - Une tour
        - Une source d'eau
        - Un chemin
    - Elle ne doit pas contenir une entité et une tour en même temps.
     */
    class Cell {
        // Cell contient soit une entité soit une tour soit (un mur/chemin ou rien, pas encore décidé )
        private Entity entity;
        private Tower tower;

        //Création d'une cellule vide;
        public Cell() {
            this.entity = null;
            this.tower = null;
        }

        //Création d'une cellule ayant un entité
        public Cell(Entity entity){
            this.entity = entity;
        }

        //Création d'une cellule ayant une tour
        public Cell(Tower tower){
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

    }

    public void print() {
        for(int i = 0; i < this.longueur; i++) {
            for(int j = 0; j < this.hauteur; j++) {
                if (this.map[i][j] != null) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
