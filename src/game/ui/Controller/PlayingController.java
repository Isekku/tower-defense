package game.ui.Controller;

import game.map.Map;
import game.ui.Vue.Playing;
import javax.swing.SwingUtilities;

public class PlayingController extends Controller{
    private Playing view;

    public PlayingController(Playing view){
        this.view = view;
    }

    public void refresh(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                infoUpdate();
            }
        });
        
    }

    public void infoUpdate(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                view.getMoneyLabel().setText("Money: " + model.getMoney());
                view.getLifeLabel().setText("Life: " + model.getLife());
                view.getWaveLabel().setText("Wave: " + model.getWave());
                view.getTimeLabel().setText("Time: " + model.getTime());
            }
        });
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void updateMap(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Map map = model.getMap();
                for (int i = 0; i < map.getHauteur(); i++){
                    for (int j = 0; j < map.getLargeur(); j++){
                        if(map.getCell(i, j) != null){
                            
                        }
                    }
                }
            }
        });
    }

    public void incrementMoney(int money){
        model.incrementMoney(money);
        infoUpdate();
    }

    public void incrementTime(){
        model.incrementTime();
        infoUpdate();
    }



}
