package game.ui.Controller;

import game.ui.Vue.Playing;

public class PlayingController extends Controller{
    private Playing view;

    public PlayingController(Playing view){
        this.view = view;
    }

    public void refresh(){
        infoUpdate();
    }

    public void infoUpdate(){
        view.getMoneyLabel().setText("Money: " + model.getMoney());
        view.getLifeLabel().setText("Life: " + model.getLife());
        view.getWaveLabel().setText("Wave: " + model.getWave());
        view.getTimeLabel().setText("Time: " + model.getTime());
    }

    public void incrementMoney(int money){
        model.incrementMoney(money);
    }



}
