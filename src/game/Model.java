package game;

import game.map.Map;

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
    }

    public void printMap(){
        System.out.println(map);
    }

}
