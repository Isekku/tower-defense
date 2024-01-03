package game.map;

import game.Entity.Entity;
import game.Entity.Mobs.Mob;
import game.Entity.Projectile;
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

    public Entity getEntity(Coordinates c){
        Cell cell = getCell(c);
        if(cell == null) return null;
        else return cell.getEntity();
    }

    // si la cellule ne contient pas de mob, elle renvoie true
    public boolean isEmpty(int x, int y){
        boolean cell = false;
        try{
            cell = getCell(x, y).getEntity() == null;
        }
        catch(NullPointerException e){
            return false;
        }
        return cell;
    }

    public void makeEmpty(Coordinates c) {
        Cell cell = null;
        try {
            cell = getCell(c);
        } catch (IndexOutOfBoundsException e) {
        }
        if (cell != null) cell.setEntity(null);
    }

    /**
     * Méthode permettant de placer un mob (Entité ou Tour) dans la map. Elle renvoie true si cela s'est bien passé et false sinon.
     * */
    public boolean setCell(int x, int y, Entity mob){
        try{
            map[x][y].setEntity(mob);
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
    public boolean setEntity(int x, int y, Entity entity){
        if(isValid(x,y)){
            if(isEmpty(x, y) || map[x][y].getEntity() instanceof Projectile){
                return setCell(x, y, entity);
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean setEntity(Coordinates c, Entity entity){
        return setEntity(c.getX(), c.getY(), entity);
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean mobOnWay(Coordinates c){
        for(int i = c.getY() + 1; i < width; i++){
            Cell cell = map[c.getX()][i];
            if(cell != null && cell.getEntity() != null && cell.getEntity() instanceof Mob) return true;
        }
        return false;
    }

    public Tower towerInFront(Coordinates c){
        Coordinates tower = new Coordinates(c.getX(), c.getY()-1);
        Entity entity = getEntity(tower);
        if(entity instanceof Tower) return (Tower) entity;
        return null;
    }

    public Mob mobInFront(Coordinates c){
        Cell cell = null;
        try{
            cell = map[c.getX()][c.getY()+1];
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
        if(cell != null && cell.getEntity() != null && cell.getEntity() instanceof Mob) return (Mob) cell.getEntity();
        else return null;
    }

    public Entity entityInFront(Coordinates c){
        return getEntity(new Coordinates(c.getX(), c.getY()+1));
    }
}
