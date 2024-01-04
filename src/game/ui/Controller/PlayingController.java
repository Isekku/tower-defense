package game.ui.Controller;

import game.map.Map;
import game.ui.Vue.Playing;

import javax.swing.*;
import java.awt.*;

public class PlayingController extends Controller{
    private Playing view;
    public int mapHeight = model.getMap().getHeight();
    public int mapWidth = model.getMap().getWidth();

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
                for (int i = 0; i < map.getHeight(); i++){
                    for (int j = 0; j < map.getWidth(); j++){
                        if(map.getCell(i, j).getEntity() != null){
                            JPanel panel = view.getMapGrid()[i][j];
                            panel.removeAll();
                            JPanel entityPanel = new JPanel(){
                                @Override
                                protected void paintComponent(Graphics g) {
                                    super.paintComponent(g);

                                    Graphics2D g2d = (Graphics2D) g.create();

                                    g2d.drawImage(view.fleurImage.getImage(), 0, 0, panel.getWidth()/2, panel.getHeight()/2, view);

                                    g2d.dispose();
                                }
                            };
                            panel.add(entityPanel);
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
