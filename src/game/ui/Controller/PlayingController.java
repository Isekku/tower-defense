package game.ui.Controller;

import game.GameState;
import game.Entity.Entity;
import game.Entity.Mobs.Mob;
import game.Entity.Projectile;
import game.Entity.towers.Tower;
import game.map.Map;
import game.ui.Vue.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayingController extends Controller{
    private Playing view;
    public int mapHeight = model.getMap().getHeight();
    public int mapWidth = model.getMap().getWidth();

    private Timer animationTimer;

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
                view.getWaveLabel().setText("Wave: " + model.getWave());
                view.getTimeLabel().setText("Time: " + model.getTime());
            }
        });
    }

    public void startRefreshing(){
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoUpdate();
                updateMap();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void startWave(){
        if (model.isWaveRunning()){
            System.out.println("combat en cours");
            return;
        }
        final boolean[] isWaveRunning = {true};
        System.out.println("Début de la vague: " + (model.getWave() + 1));
        // desactiver le bouton start wave
        view.getStartButton().setEnabled(false);
        // lancer la vague
        new Thread(){
            public void run(){
                try {
                    model.startWave();
                    view.getStartButton().setEnabled(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    isWaveRunning[0] = false;
                }
            }
        }.start();
    }

    public void updateMap(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                // on reprint la map

                // on update les cellules
                Map map = model.getMap();
                for (int i = 0; i < map.getHeight(); i++){
                    for (int j = 0; j < map.getWidth(); j++){
                        Entity e = map.getCell(i, j).getEntity();
                        if( e != null && !e.isOnMap){
                            e.isOnMap = true;
                            view.getPlayingGrid()[e.coordinates.getX()].addEntity(e);
                        }
                    }
                }

            }
        });
    }

    public void pauseWave(){
        // if (model.isWaveRunning()){
        //     model.pauseWave();
        //     model.setWaveOnBreak(false);
        //     view.getPauseButton().setText("Pause");

        // }
        // else{
            model.resumeWave();
            model.setWaveOnBreak(true);
            changeView(GameState.PAUSE);
        // }

    }

    public int whichMap(){return model.getMapType();}

    public boolean addTower(Tower tower){
        if(model.getMoney() >= tower.getCost()) return model.setTower(tower.coordinates, tower);
        return false;
    }

}
