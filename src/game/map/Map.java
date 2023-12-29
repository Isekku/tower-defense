package game.map;

import game.Entity.Entity;
import game.Entity.towers.Tower;
import game.geometry.Coordinates;

import java.awt.*;

/**
* Classe permettant la création d'une map. Elle représente la map pour la version terminale et graphique et elle contient un tableau de
* Cellule représentant chaque cellule de la map.
*/

public class Map {
    private int width;
    private int height;
    private Cell[][] map;
    private static Map instance;
    /**
     * Création d'une map avec width et height. Les cellules seront toutes initialisé à null
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Cell[height][width];
        this.makeEmptyCells();
        instance = this;
    }

    /**
     * Permet d'obtenir une cellule à partir de la map. Elle renvoie null si les valeurs indiqués ne sont pas incluses dans la map.
     * */
    public Cell getCell(int x, int y) {
        Cell cell = null;
        try{
            cell = map[x][y];
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
        return cell;
    }

    /**
     * Permet d'obtenir une cellule à partir de la map. Elle renvoie null si les valeurs indiqués ne sont pas incluse dans la map.
     * */
    public Cell getCell(Coordinates c){
        Cell cell = null;
        try{
            cell = map[c.getX()][c.getY()];
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
        return cell;
    }

    // si la cellule ne contient pas de mob, elle renvoie true
    public boolean isEmpty(int x, int y){
        boolean cell = false;
        try{
            cell = getCell(x, y).getMob() == null;
        }
        catch(NullPointerException e){
            return false;
        }
        return true;
    }

    /**
     * Méthode permettant de placer un mob (Entité ou Tour) dans la map. Elle renvoie true si cela s'est bien passé et false sinon.
     * */
    public boolean setCell(int x, int y, Entity mob){
        try{
            map[x][y].setMob(mob);
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    /**
     * Méthode permettant de placer un mob (Entité ou Tour) dans la map. Elle renvoie true si cela s'est bien passé et false sinon.
     * */
    public boolean setCell(Coordinates c, Entity mob){
        return setCell(c.getX(), c.getY(), mob);
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
     *  Permet de placer une tour si cela est possible :
     */
    public boolean setTower(int x, int y, Tower tower){
        if(isValid(x,y)){
            if(isEmpty(x, y)){
                return setCell(x, y, tower);
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean setTower(Coordinates c, Tower tower){
        return setTower(c.getX(), c.getY(), tower);
    }

    /**
     * Permet de vérifier si 2 points est bien compris dans la map.
     */
    public boolean isValid(int x, int y) {
        return (x >= 0 && y >= 0) && (x < height && y < width);
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

    public static Map getInstance() {
        return instance;
    }

    public int getHauteur() {
        return height;
    }

    public int getLargeur() {
        return width;
    }
}
