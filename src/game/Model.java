package game;

import game.map.Map;
import game.mobs.entities.Entity;
import game.mobs.towers.Tower;

public class Model {
    private GameState state;
    private Map map;
    private int money;
    private int life;
    private int wave;

    public Model() {
        this.state = GameState.MENU;
        this.map = new Map(10, 11);
        this.money = 100;
        this.life = 10;
        this.wave = 0;

        map.setSell(5, 5, new Tower(5, 5, 0, 0, 0, 0, 0));
        map.setSell(6, 6, new Entity(6, 6, 0, 0, 0));

    }

    public void printMap(){
        System.out.println(map);
    }

}
