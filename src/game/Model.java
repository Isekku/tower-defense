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
        this.map = null;
        this.money = 100;
        this.life = 10;
        this.wave = 0;
    }
}
