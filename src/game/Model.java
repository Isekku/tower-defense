package game;

import game.Entity.Entity;
import game.Entity.Mobs.Mob;
import game.Entity.towers.*;
import game.geometry.Coordinates;
import game.map.Map;

import java.util.ArrayList;

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
    public ArrayList<Coordinates> towerEmplacement = new ArrayList<>();
    public ArrayList<Coordinates> mobEmplacement = new ArrayList<>();

    public Model() {
        if (instance != null) {
            System.out.println(getClass().getName() + " already instantiated");
            throw new IllegalStateException("Already instantiated");
        }
        else {
            System.out.println("Model created");
        }
        //this.state = GameState.MENU;
        this.map = new Map(11, 10);
        this.money = 100;
        this.life = 10;
        this.wave = 0;
        this.timeOfWave = 10;
        //Ajout des tours jouable :
        towerExample.add(new BasicTower());
        towerExample.add(new ElectricTower());
        towerExample.add(new IceTower());
        towerExample.add(new RoyalTower());
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
            towerEmplacement.add(value);
        }
        return valid;
    }

    public boolean setMob(Coordinates value, Mob mob){
        boolean valid = map.setEntity(value, mob);
        if(valid) mobEmplacement.add(value);
        return true;
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
            for(Coordinates c : mobEmplacement){
                if(towerInFront(c)){
                    Mob mob = (Mob) map.getEntity(c);
                    Coordinates t = new Coordinates(c.getX(), c.getY()-1);
                    Tower tower = (Tower) map.getEntity(t);
                    boolean dead = mob.makeDamage(tower);
                    if(dead){
                        map.makeEmpty(t);
                        towerEmplacement.remove(t);
                    }
                }
                else{
                    moveAsMob(c);
                }
            }
            printMap();
            Thread.sleep(3000);
        }
        System.out.println("Vous avez perdu :( (cheh) ! ");
        System.exit(0);
    }

    public int mobOnWay(Coordinates c){
        return map.mobOnWay(c);
    }

    public boolean towerInFront(Coordinates c){
        return map.towerInFront(c);
    }

    private void moveAsMob(Coordinates c){
        Mob mob = (Mob) map.getEntity(c);

        map.makeEmpty(c);

        c.moveLeft();
        map.setEntity(c, mob);
    }

    public boolean lose(){
        for(Coordinates c : mobEmplacement){
            if(c.getY() < 0){
                return true;
            }
        }
        return false;
    }
}
