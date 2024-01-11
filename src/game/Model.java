package game;

import game.Entity.Entity;
import game.Entity.Mobs.*;
import game.Entity.Projectile;
import game.Entity.towers.*;
import game.geometry.Coordinates;
import game.map.Map;
import game.ui.Terminal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import static game.ui.Style.*;


public class Model {
    private static final Model instance = new Model();
   // private GameState state;
    private Map map;
    private int money;
    private int wave;
    private int difficulty;
    private int mapType = -1;
    private int mode = -1;
    private int time;
    private int waveTime;
    private int timeOfWave;
    private boolean waveOnBreak = false;
    private boolean waveRunning = false;
    private boolean haveClickedOnTower = false;
    private int clickedTower;
    private String clickedTowerImage;

    //Ajout des tours jouable et des mobs possible :
    public ArrayList<Tower> towerExample = new ArrayList<>();
    public ArrayList<Mob> mobExample = new ArrayList<>();

    //L'emplacement des tours, mobs et projectile :
    public CopyOnWriteArrayList<Tower> towerEmplacement = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Mob> mobEmplacement = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Projectile> projectileEmplacement = new CopyOnWriteArrayList<>();

    //L'emplacement des mob de la prochaine wave :
    public ArrayList<Mob> mobInWave = new ArrayList<>();

    public Model() {
        if (instance != null) {
            System.out.println(getClass().getName() + " already instantiated");
            throw new IllegalStateException("Already instantiated");
        }
        else {
            System.out.println("Model created");
        }
        //this.state = GameState.MENU;
        this.map = new Map(5, 9);
        this.money = 50;
        this.wave = 0;
        this.timeOfWave = 10;

        Coordinates coordinatesNull = new Coordinates(-1, -1, 1);

        //Ajout des tours jouable :
        towerExample.add(new BasicTower(coordinatesNull));
        towerExample.add(new ElectricTower(coordinatesNull));
        towerExample.add(new IceTower(coordinatesNull));
        towerExample.add(new RoyalTower(coordinatesNull));

        //Ajout des mobs possible :
        mobExample.add(new WeakMob(coordinatesNull));
        mobExample.add(new NormalMob(coordinatesNull));
        mobExample.add(new HeavyMob(coordinatesNull));
        mobExample.add(new StrongerMob(coordinatesNull));
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

        System.out.print('\n' + stringBase + stringCouleurVert + "Money: " + money + "; ");
        System.out.println(stringBase + stringCouleurCyan + "Wave: " + wave + "; ");

        System.out.println(stringBase + map);

        //System.out.print(stringBase + stringCouleurCyan + "Difficulty: " + difficulty + "; ");
        //System.out.print(stringBase + stringCouleurJaune + "Mode: " + mode + "; ");
        //System.out.println(stringBase + "Map: " + mapType + "; ");
    }

    public void printWithoutMap(){
        System.out.print(stringBase + stringCouleurVert + "Money: " + money + "; ");
        System.out.println(stringBase + stringCouleurCyan + "Wave: " + wave + "; " + '\n');
    }

    public int getMoney() {
        return money;
    }

    public CopyOnWriteArrayList<Mob> getMobEmplacement() {
        return mobEmplacement;
    }

    public CopyOnWriteArrayList<Projectile> getProjectileEmplacement() {
        return projectileEmplacement;
    }

    public CopyOnWriteArrayList<Tower> getTowerEmplacement() {
        return towerEmplacement;
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
    
    public boolean isWaveOnBreak(){
        return waveOnBreak;
    }

    public void incrementWave(){
        wave++;
    }

    public void incrementTimeOfWave(int i){
        timeOfWave += i;
    }

    public void setWaveOnBreak(boolean waveRunning){
        this.waveOnBreak = waveRunning;
    }

    public void setWaveRunning(boolean waveRunning){
        this.waveRunning = waveRunning;
    }

    public boolean isWaveRunning(){
        return waveRunning;
    }

    public void setWaveTime(int waveTime){
        this.waveTime = waveTime;
    }

    public int getTimeOfWave(){
        return timeOfWave;
    }

    public boolean isHaveClickedOnTower() {
        return haveClickedOnTower;
    }

    public int getClickedTower() {
        return clickedTower;
    }

    public void setClickedTower(int towerClicked) {
        this.clickedTower = towerClicked;
    }

    public void setHaveClickedOnTower(boolean haveClickedOnTower) {
        this.haveClickedOnTower = haveClickedOnTower;
    }

    public String getClickedTowerImage() {
        return clickedTowerImage;
    }

    public void setClickedTowerImage(String clickedTowerImage) {
        this.clickedTowerImage = clickedTowerImage;
    }

    public int getWaveTime(){
        return waveTime;
    }

    public void projectileRound(){
        ArrayList<Projectile> deadProjectile = new ArrayList<>();
        for(Projectile p : projectileEmplacement){

            Mob m = mobInCell(new Coordinates(p.coordinates.getX(), p.coordinates.getY()-1, p.coordinates.speed));//map.getEntity(p.coordinates);

            if(!map.isValid(p.coordinates)){
                p.towerParent.canShoot = true;
                deadProjectile.add(p);
                p.takeDamage(p.getPv());
            }
            else if(m != null){
                boolean dead = p.makeDamage(m);
                if(dead){
                    mobEmplacement.remove(m);
                    map.makeEmpty(m.coordinates);
                    incrementMoney(m.value);
                    m.timer.stop();
                }
                p.towerParent.canShoot = true;
                if(map.getEntity(p.coordinates) == p) map.makeEmpty(p.coordinates);
                p.takeDamage(p.getPv());
                deadProjectile.add(p);
            }

            if(map.isValid(p.coordinates) && p.getPv() > 0) moveAsProjectile(p);
        }
        projectileEmplacement.removeAll(deadProjectile);
    }

    public void towerRound(){
        for(Tower t : towerEmplacement){
            projectileEmplacement.addAll(t.projectileShooted);
            t.projectileShooted.clear();
            if(map.getEntity(t.coordinates) == null) map.setEntity(t.coordinates, t);
            if((mobOnWay(t.coordinates) || mobInCell(t.coordinates) != null)){
                if(!t.timer.isRunning()) t.timer.start();
            }
            else t.timer.stop();
        }
    }

    public void mobRound(){
        for(Mob m : mobEmplacement){
            towerEmplacement.removeAll(m.towerKilled);
            m.towerKilled.clear();
            if(towerInCell(m.coordinates) !=  null && !m.timer.isRunning()){
                m.currentTower = towerInCell(m.coordinates);
                m.timer.start();
                m.currentImage = m.entityAttack;
            }
            else if (towerInCell(m.coordinates) == null){
                if(m.timer.isRunning()) m.timer.stop();
                m.currentImage = m.entityWalk;
                moveAsMob(m);
            }
            /*
            m.isKilling = false;
            if(map.getEntity(m.coordinates) == null) map.setEntity(m.coordinates, m);

            Tower t = towerInCell(m.coordinates);//map.getEntity(m.coordinates); //towerInFront(m.coordinates);
            if(t != null){
                m.currentImage = m.entityAttack;
                m.isKilling = true;
                boolean dead = m.makeDamage(t);
                if(dead){
                    towerEmplacement.remove(t);
                    map.makeEmpty(t.coordinates);
                    m.currentImage = m.entityWalk;
                }
            }
            else if(!m.isKilling) moveAsMob(m);

             */
        }
    }

    public void startWave() throws InterruptedException{
        waveRunning = true;
        Terminal.clearScreen();
        print();

        boolean temp = true;
        addMobInWave(wave);

        Timer wave = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mobRound();
                towerRound();
                projectileRound();
            }
        });
        wave.start();

        Timer mapPrint = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Terminal.clearScreen();
                print();
                System.out.print("Voulez-vous placer une tour ? (1) Oui : ");
                incrementTime();
            }
        });

        Timer t = setMobInWave();
        mapPrint.start();

        while(temp || !lose() && !winWave()){
            if(!mobInWave.isEmpty());
            else t.stop();
            temp = false;

            if(!wave.isRunning()) wave.start();
            if(!mapPrint.isRunning()) mapPrint.start();
            if(!waveOnBreak) {
                if (!mobInWave.isEmpty()) {
                    t.start();
                }

            }
            else{
                t.stop();
                wave.stop();
                mapPrint.stop();
            }
        }

        if(lose()){
            System.out.println("Vous avez perdu :( ! ");
            System.exit(0);
        }
        else {
            wave.stop();
            mapPrint.stop();
            Terminal.clearScreen();
            print();
            waveRunning = false;
            projectileEmplacement.clear();
            mobEmplacement.clear();
            System.out.println('\n' + "Appuyez sur entrée pour continuer");
            incrementWave();
        }
        if(mode == 2) startWave();
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

    public void pauseWave(){
        waveRunning = false;
    }

    public void resumeWave(){
        waveRunning = true;
    }

    public Entity entityInFront(Coordinates c){
        return map.entityInFront(c);
    }

    private void moveAsMob(Mob mob){
        map.makeEmpty(mob.coordinates);

        mob.coordinates.moveLeft();

        if(!(map.getEntity(mob.coordinates) instanceof Mob || map.getEntity(mob.coordinates) instanceof Tower)){
            map.setEntity(mob.coordinates, mob);
        }
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
        return mobEmplacement.isEmpty() && projectileEmplacement.isEmpty() && mobInWave.isEmpty();
    }

    public void makeBreak(){
        waveOnBreak = true;
        System.out.println(stringCouleurJaune + "Normalement je suis en pause" + stringBase);
    }

    public void restartWave(){
        waveOnBreak = false;
    }

    public Mob mobInCell(Coordinates c){
        for(Mob m : mobEmplacement){
            if(m.coordinates.getX() == c.getX() && m.coordinates.getY() == c.getY()) return m;
        }
        return null;
    }

    public Tower towerInCell(Coordinates c){
        for(Tower t : towerEmplacement){
            if(t.coordinates.getX() == c.getX() && t.coordinates.getY() == c.getY()) return t;
        }
        return null;
    }

    public void addMobInWave(int wave){
        Random r = new Random();
        for(int i = 0; i < ((wave+1) + r.nextInt(0,difficulty+1)); i++){
            if(wave == 0){
                Mob m = mobExample.get(0).clone(new Coordinates(0, map.getWidth()-1, mobExample.get(0).speed));
                mobInWave.add(m);
            }
            else{
                int emplacement = r.nextInt(map.getHeight());
                int mobBound;
                if(wave < 2) mobBound = 0;
                else if(wave < 4) mobBound = r.nextInt(0, 2);
                else if(wave < 6) mobBound = r.nextInt(0, 3);
                else mobBound = r.nextInt(0, 4);
                Mob m = mobExample.get(mobBound).clone(new Coordinates(emplacement, map.getWidth()-1, mobExample.get(mobBound).speed));
                mobInWave.add(m);
            }
        }
    }

    public Timer setMobInWave(){
        Timer t = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!mobInWave.isEmpty()){
                    Mob m = mobInWave.get(0);
                    if(map.getEntity(m.coordinates) == null){
                        setMob(m.coordinates, m);
                        mobInWave.remove(m);
                    }
                }
            }
        });
        return t;
    }

    public void save() {
        System.out.println("Sauvegarde en cours...");
        Save save = Save.getInstance();
        // save.save();
    }
}
