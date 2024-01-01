package game;

import game.Entity.Entity;
import game.Entity.Mobs.Mob;
import game.Entity.Projectile;
import game.Entity.towers.*;
import game.geometry.Coordinates;
import game.map.Map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import static game.ui.Style.*;


public class Model {
    private static final Model instance = new Model();
   // private GameState state;
    private Map map;
    private int money;
    private int life;
    private int wave;
    private int difficulty = -1;
    private int mapType = -1;
    private int mode = -1;
    private int time;
    private int waveTime;
    private int timeOfWave;
    private boolean waveRunning = true;

    //Ajout des tours jouable, leur emplacements et l'emplacement des mobs :
    public ArrayList<Tower> towerExample = new ArrayList<>();
    public ArrayList<Tower> towerEmplacement = new ArrayList<>();
    public ArrayList<Mob> mobEmplacement = new ArrayList<>();
    public ArrayList<Projectile> projectileEmplacement = new ArrayList<>();

    public Model() {
        if (instance != null) {
            System.out.println(getClass().getName() + " already instantiated");
            throw new IllegalStateException("Already instantiated");
        }
        else {
            System.out.println("Model created");
        }
        //this.state = GameState.MENU;
        this.map = new Map(9, 11);
        this.money = 1000;
        this.life = 10;
        this.wave = 0;
        this.timeOfWave = 10;
        //Ajout des tours jouable :
        Coordinates coordinatesNull = new Coordinates(-1, -1);
        towerExample.add(new BasicTower(coordinatesNull));
        towerExample.add(new ElectricTower(coordinatesNull));
        towerExample.add(new IceTower(coordinatesNull));
        towerExample.add(new RoyalTower(coordinatesNull));
    }

    public void printMap(){
        System.out.println(stringBase + map);
    }

    public static Model getInstance(){
        return Model.instance;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
        time = 0;
    }

    public void setMapType(int mapType){
        this.mapType = mapType;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    public boolean setTower(Coordinates value, Tower tower){
        boolean valid = map.setEntity(value, tower);
        if(valid){
            money -= tower.getCost();
            towerEmplacement.add(tower);
            towerEmplacement.sort(new Comparator<Tower>() {
                @Override
                public int compare(Tower t1, Tower t2) {
                    return Integer.compare(t2.coordinates.getY(), t1.coordinates.getY());
                }
            });
        }
        return valid;
    }

    public boolean setMob(Coordinates value, Mob mob){
        boolean valid = map.setEntity(value, mob);
        if(valid){
            mobEmplacement.add(mob);
            mobEmplacement.sort(new Comparator<Mob>() {
                @Override
                public int compare(Mob m1, Mob m2) {
                    return Integer.compare(m1.coordinates.getY(), m2.coordinates.getY());
                }
            });
        }
        return valid;
    }

    public void reset(){

    }

    public void print(){
       // System.out.println("state: " + state);
        //System.out.println("map: " + '\n' + map);

        System.out.print(stringBase + stringCouleurVert + "Money: " + money + "; ");
        System.out.print(stringBase + stringCouleurRouge + "Life: " + life + "; ");
        System.out.println(stringBase + stringCouleurCyan + "Wave: " + wave + "; ");

        System.out.println(stringBase + map);

        //System.out.print(stringBase + stringCouleurCyan + "Difficulty: " + difficulty + "; ");
        //System.out.print(stringBase + stringCouleurJaune + "Mode: " + mode + "; ");
        //System.out.println(stringBase + "Map: " + mapType + "; ");
    }

    public void printWithoutMap(){
        System.out.print(stringBase + stringCouleurVert + "Money: " + money + "; ");
        System.out.print(stringBase + stringCouleurRouge + "Life: " + life + "; ");
        System.out.println(stringBase + stringCouleurCyan + "Wave: " + wave + "; " + '\n');
    }

    public int getMoney() {
        return money;
    }

    public int getLife() {
        return life;
    }

    public int getWave() {
        return wave;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getMapType() {
        return mapType;
    }

    public int getMode() {
        return mode;
    }

    public Map getMap() {
        return map;
    }

    public int getTime() {
        return time;
    }
    public int getTowerNumber(){
        return towerEmplacement.size();
    }

    public int getMobNumber(){
        return mobEmplacement.size();
    }

    public void incrementMoney(int money) {
        this.money += money;
    }

    public void incrementTime(){
        time++;
    }

    public void incrementWaveTime(){
        waveTime++;
    }
    
    public boolean isWaveRunning(){
        return waveRunning;
    }

    public void incrementWave(){
        wave++;
    }

    public void incrementTimeOfWave(int i){
        timeOfWave += i;
    }

    public void setWaveRunning(boolean waveRunning){
        this.waveRunning = waveRunning;
    }

    public void setWaveTime(int waveTime){
        this.waveTime = waveTime;
    }

    public int getTimeOfWave(){
        return timeOfWave;
    }

    public int getWaveTime(){
        return waveTime;
    }

    public void startWaveTemp() throws InterruptedException{
        while(!lose() && !winWave()){
            if(waveRunning) {

                //Le tour des Towers
                for(Tower t : towerEmplacement){
                    if(mobOnWay(t.coordinates)){
                        Projectile p = t.shoot(t, map.getWidth(), map.getHeight());
                        projectileEmplacement.add(p);
                        t.canShoot = false;
                    }
                }

                //Le tour des Mobs
                for(Mob m : mobEmplacement){
                    Tower t = towerInFront(m.coordinates);

                    if(t != null){
                        System.out.println("Mob à attaqué Tower : " + t.getNom());
                        boolean dead = m.makeDamage(t);
                        if(dead) towerEmplacement.remove(t);
                    }

                    else moveAsMob(m);
                }


                 ArrayList<Projectile> deadProjectile = new ArrayList<>();
                 for(Projectile p : projectileEmplacement){
                    Entity e = map.getEntity(p.coordinates);

                    if(e instanceof Mob){
                        Mob m = (Mob) e;
                        boolean dead = p.makeDamage(e);
                        System.out.println("Projectile à touché Mob : " + p.towerParent.nom);
                        if(dead){
                            mobEmplacement.remove(m);
                            incrementMoney(m.value);
                            p.towerParent.canShoot = true;
                        }
                        deadProjectile.add(p);
                    }

                    else moveAsProjectile(p);
                }
                projectileEmplacement.removeAll(deadProjectile);

                print();
                System.out.println("Voulez vous placer une tour ? (1) Oui : ");
                Thread.sleep(1000);
            }
        }
        if(lose()){
            System.out.println("Vous avez perdu :( (cheh) ! ");
            System.exit(0);
        }
        else System.out.println('\n' + "Appuyez sur entrée pour continuer");
    }

    public boolean mobOnWay(Coordinates c){
        return map.mobOnWay(c);
    }

    public Tower towerInFront(Coordinates c){
        return map.towerInFront(c);
    }

    public Mob mobInFront(Coordinates c){
        return map.mobInFront(c);
    }

    public Entity entityInFront(Coordinates c){
        return map.entityInFront(c);
    }

    private void moveAsMob(Mob mob){
        System.out.println("C'est ici : " + mob.coordinates);

        map.makeEmpty(mob.coordinates);

        mob.coordinates.moveLeft();
        map.setEntity(mob.coordinates, mob);
    }

    public void moveAsProjectile(Projectile projectile){
        if(map.getEntity(projectile.coordinates) == projectile) map.makeEmpty(projectile.coordinates);
        projectile.projectileMap.makeEmpty(projectile.coordinates);

        projectile.coordinates.moveRight();

        if(map.getEntity(projectile.coordinates) == null)map.setEntity(projectile.coordinates, projectile);
        projectile.projectileMap.setEntity(projectile.coordinates, projectile);
    }

    public boolean lose(){
        for(Mob mob : mobEmplacement){
            if(mob.coordinates.getY() < 0){
                return true;
            }
        }
        return false;
    }

    public boolean winWave(){
        return mobEmplacement.isEmpty() && projectileEmplacement.isEmpty();
    }

    public void makeBreak(){
        waveRunning = false;
    }

    public void restartWave(){
        waveRunning = true;
    }

    public Coordinates getCoordinate(ArrayList<Coordinates> list, Coordinates comp){
        for(int i = 0; i < list.size(); i++){
            Coordinates c = list.get(i);
            if(c.getX() == comp.getX() && c.getY() == comp.getY()) return c;
        }
        return null;
    }
}
