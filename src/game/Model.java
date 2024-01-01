package game;

import game.Entity.Entity;
import game.Entity.Mobs.Mob;
import game.Entity.Projectile;
import game.Entity.towers.*;
import game.geometry.Coordinates;
import game.map.Map;

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
    private boolean waveRunning;

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

        System.out.print(stringBase + map);

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
        while(!lose()){

            ArrayList<Projectile> deadProjectile = new ArrayList<>();
            for(Projectile projectile : projectileEmplacement){
                System.out.println(projectile.coordinates);
                if(!map.isValid(projectile.coordinates)) deadProjectile.add(projectile);
                else{
                    Entity entity = map.getEntity(projectile.coordinates);
                    Mob mob = null;

                    if(entityInFront(projectile.coordinates) == null) moveAsProjectile(projectile);
                    else if(!(entityInFront(projectile.coordinates) instanceof Mob)){
                        if(map.getEntity(projectile.coordinates) == projectile) map.makeEmpty(projectile.coordinates);
                        projectile.coordinates.moveRight();
                    }


                    if(entity == null || entity instanceof Projectile){
                        entity = entityInFront(projectile.coordinates);
                    }
                    if(entity instanceof Mob){
                        mob = (Mob) entity;
                    }

                    if(mob != null){
                        System.out.println("Mob en face : " + projectile.towerParent.getNom());
                        projectile.makeDamage(entity);
                        map.makeEmpty(projectile.coordinates);
                        deadProjectile.add(projectile);
                        projectile.towerParent.canShoot = true;
                    }
                }
            }
            projectileEmplacement.removeAll(deadProjectile);

            ArrayList<Mob> deadMob = new ArrayList<>();
            for(Mob mob : mobEmplacement){
                System.out.println("Emplacement mob : " + mob.coordinates);
                Coordinates mobCoordinate = mob.coordinates;
                Tower towerInFront = towerInFront(mobCoordinate);

                if(map.getEntity(mob.coordinates) == null) map.setEntity(mob.coordinates, mob);

                if(mob.getPv() <= 0){
                    deadMob.add(mob);
                    if(map.getEntity(mob.coordinates) == mob)map.makeEmpty(mob.coordinates);
                }

                else if(towerInFront != null){
                    boolean dead = mob.makeDamage(towerInFront);
                    if(dead){
                        map.makeEmpty(towerInFront.coordinates);
                        towerEmplacement.remove(towerInFront);
                    }
                }

                else{
                    if(map.getEntity(new Coordinates(mob.coordinates.getX(), mob.coordinates.getY()-1)) != null) map.makeEmpty(mob.coordinates);
                    else moveAsMob(mob.coordinates);
                }
            }
            mobEmplacement.removeAll(deadMob);

            for(Tower tower : towerEmplacement){
                if(mobOnWay(tower.coordinates) && tower.canShoot){
                    tower.canShoot = false;
                    Projectile towerProjectile = tower.shoot(tower, map.getWidth(), map.getHeight());
                    if(map.getEntity(towerProjectile.coordinates) == null) map.setEntity(towerProjectile.coordinates, towerProjectile);
                    projectileEmplacement.add(towerProjectile);
                }
                if(map.getEntity(tower.coordinates) == null) map.setEntity(tower.coordinates, tower);
            }

            printMap();
            Thread.sleep(1000);
        }
        System.out.println("Vous avez perdu :( (cheh) ! ");
        System.exit(0);
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

    private void moveAsMob(Coordinates c){
        Mob mob = (Mob) map.getEntity(c);

        map.makeEmpty(c);

        c.moveLeft();
        map.setEntity(c, mob);
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

    public Coordinates getCoordinate(ArrayList<Coordinates> list, Coordinates comp){
        for(int i = 0; i < list.size(); i++){
            Coordinates c = list.get(i);
            if(c.getX() == comp.getX() && c.getY() == comp.getY()) return c;
        }
        return null;
    }
}
