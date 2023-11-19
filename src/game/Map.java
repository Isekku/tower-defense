package game;

import game.entities.Entity;
import game.tower.Tower;

public class Map {
    private int longueur;
    private int hauteur;
    private Cell[][] map;


    class Cell {
        // cell contient soit une entité soit une tour soit (un mur/chemin ou rien, pas encore décidé )
        private Entity entity;
        private Tower tower;

        public Cell() {
            this.entity = null;
            this.tower = null;
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
