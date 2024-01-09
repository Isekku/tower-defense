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
    private int difficulty = -1;
    private int mapType = -1;
    private int mode = -1;
    private int time;
    private int waveTime;
    private int timeOfWave;
    private boolean waveOnBreak = false;
    private boolean waveRunning = false;

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

        System.out.print(stringBase + stringCouleurVert + "Money: " + money + "; ");
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

    public int getWaveTime(){
        return waveTime;
    }

    public void startWave() throws InterruptedException{

        Terminal.clearScreen();
        print();

        boolean temp = true;
        addMobInWave(wave);

        Timer wave = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Tower t : towerEmplacement){
                    if(mobOnWay(t.coordinates) && t.canShoot){
                        t.currentImage = t.entityAttack;
                        t.canShoot = false;
                        Projectile p = t.shoot(t, map.getWidth(), map.getHeight());
                        projectileEmplacement.add(p);
                        t.currentImage = t.entityWalk;
                    }
                }

                ArrayList<Projectile> deadProjectile = new ArrayList<>();
                for(Projectile p : projectileEmplacement){
                    moveAsProjectile(p);

                    Mob m = mobInFront(p.coordinates);
                    Entity e2 = map.getEntity(p.coordinates);

                    if(!map.isValid(p.coordinates)){
                        p.towerParent.canShoot = true;
                        deadProjectile.add(p);
                    }
                    else if(e2 instanceof Mob){
                        Mob m2 = (Mob) e2;
                        boolean dead = p.makeDamage(m2);
                        if(dead){
                            mobEmplacement.remove(m2);
                            map.makeEmpty(m2.coordinates);
                            incrementMoney(m2.value);
                        }
                        p.towerParent.canShoot = true;
                        if(map.getEntity(p.coordinates) == p) map.makeEmpty(p.coordinates);
                        deadProjectile.add(p);
                    }

                    else if(m != null){
                        boolean dead = p.makeDamage(m);
                        if(dead){
                            mobEmplacement.remove(m);
                            map.makeEmpty(m.coordinates);
                            incrementMoney(m.value);
                        }
                        p.towerParent.canShoot = true;
                        if(map.getEntity(p.coordinates) == p) map.makeEmpty(p.coordinates);
                        deadProjectile.add(p);
                    }
                }
                projectileEmplacement.removeAll(deadProjectile);


                for(Mob m : mobEmplacement){

                    if(map.getEntity(m.coordinates) == null) map.setEntity(m.coordinates, m);

                    Tower t = towerInFront(m.coordinates);
                    if(t != null){
                        m.isKilling = true;
                        boolean dead = m.makeDamage(t);
                        if(dead){
                            towerEmplacement.remove(t);
                            map.makeEmpty(t.coordinates);
                        }
                    }
                    else if(!m.isKilling) moveAsMob(m);
                    m.isKilling = false;
                }
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
            System.out.println("Vous avez perdu :( (cheh) ! ");
            System.exit(0);
        }
        else {
            wave.stop();
            mapPrint.stop();
            print();
            projectileEmplacement.clear();
            mobEmplacement.clear();
            System.out.println('\n' + "Appuyez sur entrée pour continuer");
            incrementWave();
        }
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

        if(!(map.getEntity(mob.coordinates) instanceof Mob)){
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

    public void addMobInWave(int wave){
        Random r = new Random();
        for(int i = 0; i < ((wave+1) + r.nextInt(0,2)); i++){
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
        /*
        ArrayList<Mob> mobRemovable = new ArrayList<>();
        for(Mob m : mobInWave){
            try{
                Thread.sleep(3000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            if(map.getEntity(m.coordinates) == null){
                setMob(m.coordinates, m);
                mobRemovable.add(m);
            }
        }
        mobInWave.removeAll(mobRemovable);
         */

        Timer t = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!mobInWave.isEmpty()){
                    Mob m = mobInWave.getFirst();
                    if(map.getEntity(m.coordinates) == null){
                        setMob(m.coordinates, m);
                        mobInWave.remove(m);
                    }
                }
            }
        });
        return t;
    }
}
