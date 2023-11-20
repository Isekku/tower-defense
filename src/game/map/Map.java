package game.map;

import game.entities.Entity;
import game.tower.Tower;
import game.geometry.Coordinates;

import java.awt.*;

/*
Class Map :

- Elle sert à créer la map.
- Elle est pareil pour la version terminale et graphique.
- Elle contient une classe interne Cell qui représente chaque cellule de la map

*/

public class Map {
    private int width;
    private int height;
    private Cell[][] map;

    /**
     * Création d'une map avec width et height. Les cellules seront toutes initialisé à null
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Cell[height][width];
        this.makeEmptyCells();
    }

    public Cell getCell(int x, int y) {
        return null;
    }

    /**
     * Permet d'initialisé toutes cellules ayant la valeur null dans map avec une cellule vide
     */
    public void makeEmptyCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                if (map[i][j] == null) map[i][j] = new Cell();
        }
    }

    /**
     * Permet de vérifier si 2 points est bien compris dans la map.
     */
    public boolean isValid(int x, int y) {
        return (x > 0 && y > 0) && (x <= width && y <= height);
    }

    /**
     * Permet de vérifier si une coordonnées est bien présente dans la map.
     */
    public boolean isValid(Coordinates c) {
        return isValid(c.getX(), c.getY());
    }

    /**
     * Permet de placer une tour dans la map si les coordonnées fournis sont valides
     */

        public String toString () {
            String res = "    ";
            char letter = 'A';
            for (int i = 0; i < width; i++) {
                res += (i + 1) + "  ";
            }
            for (int i = 0; i < this.height; i++) {
                res += "\n" + letter + " | ";
                letter++;
                for (int j = 0; j < this.width; j++) {
                    res += map[i][j].toString();
                }
            }
            return res;
        }
}
