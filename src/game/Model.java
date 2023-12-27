package game;

import game.map.Map;
import game.mobs.entities.Entity;
import game.mobs.towers.Tower;


public class Model {
    private static final Model instance = new Model();
   // private GameState state;
    private Map map;
    private int money;
    private int life;
    private int wave;
    private int difficulty;
    private int mapType;
    private int mode;
    private int time;
    private int waveTime;
    private boolean waveRunning;

    public Model() {
        if (instance != null) {
            System.out.println(getClass().getName() + " already instantiated");
            throw new IllegalStateException("Already instantiated");
        }
        else {
            System.out.println("Model created");
        }
        //this.state = GameState.MENU;
        this.map = new Map(10, 11);
        this.money = 100;
        this.life = 10;
        this.wave = 0;
        this.difficulty = 0;

        map.setSell(5, 5, new Tower(5, 5, 0, 0, 0, 0, 0));
        map.setSell(6, 6, new Entity(6, 6, 0, 0, 0));

    }

    public void printMap(){
        System.out.println(map);
    }

    public static Model getInstance(){
        return Model.instance;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
        print();
        time = 0;
    }

    public void setMapType(int mapType){
        this.mapType = mapType;
        print();
    }

    public void setMode(int mode){
        this.mode = mode;
        print();
    }

    public void reset(){

    }

    public void print(){
       // System.out.println("state: " + state);
        System.out.println("map: " + '\n' + map);
        System.out.println("money: " + money);
        System.out.println("life: " + life);
        System.out.println("wave: " + wave);
        System.out.println("difficulty: " + difficulty);
        System.out.println("mode: " + mode);
        System.out.println("mapType: " + mapType);
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
    
    public boolean isWaveRunning(){
        return waveRunning;
    }

    public void startWave() throws InterruptedException {
        waveRunning = true;
        wave++;
        waveTime = 0;
        while (waveTime < 10) { // faudra changer ça par un truc qui vérifie si tous les mobs sont morts ou pas et changer les 10s
            System.out.println("waveTime: " + waveTime);
            Thread.sleep(1000);
            waveTime++;
        }
        waveRunning = false;
    }

}
