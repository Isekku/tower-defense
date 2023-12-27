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

    public void startWave(){
        if (model.isWaveRunning()){
            System.out.println("combat en cours");
            return;
        } 
        System.out.println("Début de la vague: " + (model.getWave() + 1));
        new Thread(){
            public void run(){
                try {
                    model.startWave();
                    while(model.isWaveRunning()){
                        refresh();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void incrementMoney(int money){
        model.incrementMoney(money);
    }



}
