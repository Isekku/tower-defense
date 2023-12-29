package game;

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

    //Ajout des tours jouable :
    public ArrayList<Tower> towerExample = new ArrayList<>();

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
        boolean valid = map.setTower(value, tower);
        if(valid) money -= tower.getCost();
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

    public void incrementMoney(int money) {
        this.money += money;
    }

    public void incrementTime(){
        time++;
    }
    
    public boolean isWaveRunning(){
        return waveRunning;
    }

    public void startWave() throws InterruptedException {
        waveRunning = true;
        wave++;
        waveTime = 0;
        System.out.println("La vague va durer: " + timeOfWave + "s");
        while (waveTime <= timeOfWave) { // faudra changer ça par un truc qui vérifie si tous les mobs sont morts ou pas et changer les 10s par le temps de la vague
            System.out.println("waveTime: " + waveTime);
            Thread.sleep(1000);
            waveTime++;
            time++;
        }
        System.out.println("Fin de la vague: " + wave);
        waveRunning = false;
        timeOfWave += 5;
    }

}
