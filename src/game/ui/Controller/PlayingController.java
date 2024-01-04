package game.ui.Controller;

import game.Entity.Mobs.NormalMob;
import game.geometry.Coordinates;
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
            }
        }.start();
         
    }

    public void updateMap(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                // on reprint la map
                view.printMap();
                
                // on update les cellules
                Map map = model.getMap();
                // on ajoute un Normalmob pour tester
                NormalMob mob = new NormalMob(new Coordinates(9, 0));
                map.getCell(0, 0).setEntity(mob);
                for (int i = 0; i < map.getHeight(); i++){
                    for (int j = 0; j < map.getWidth(); j++){
                        if(map.getCell(i, j) != null){
                            // map.getCell(i, j).update();
                            // afficher les mobs
                            System.out.println(mob);
                            System.out.println();
                            System.out.println(map.getCell(i, j).getEntity());

                        }
                    }
                }

            }
        });
    }

    public void pauseWave(){
        if (model.isWaveRunning()){
            model.pauseWave();
            model.setWaveOnBreak(false);
            view.getPauseButton().setText("Pause");

        }
        else{
            model.resumeWave();
            model.setWaveOnBreak(true);
            view.getPauseButton().setText("Resume");
        }
    }

    public void incrementMoney(int money){
        model.incrementMoney(money);
        model.printWithoutMap(); // terminal
        infoUpdate(); // graphic
    }

    public void incrementTime(){
        model.incrementTime();
        infoUpdate();
    }



}
