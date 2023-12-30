package game.ui.Controller;

import game.map.Map;
import game.ui.Vue.Playing;

import javax.swing.JPanel;
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
                    startWaveThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void startWaveThread() throws InterruptedException {
        model.setWaveRunning(true); 
        model.incrementWave();
        model.setWaveTime(0);
        System.out.println("La vague va durer: " + model.getTimeOfWave() + "s");
        while (model.getWaveTime() <= model.getTimeOfWave()) { // faudra changer ça par un truc qui vérifie si tous les mobs sont morts ou pas et changer les 10s par le temps de la vague
            System.out.println("waveTime: " + model.getWaveTime());
            Thread.sleep(1000);
            infoUpdate();
            model.incrementWaveTime();
            model.incrementTime();

        }
        System.out.println("Fin de la vague: " + model.getWave());
        model.setWaveRunning(false);
        model.incrementTimeOfWave(5);
        model.incrementTime();
    }


    public void updateMap(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Map map = model.getMap();
                System.out.println("Hauteur/Longueur de la Map : " + map.getHauteur() + " " + map.getLargeur());
                for (int i = 0; i < map.getHauteur(); i++){
                    for (int j = 0; j < map.getLargeur(); j++){
                        if(map.getCell(i, j) != null){
                            JPanel cell = view.getMapGridPanel(i, j);
                            switch (map.getCell(i, j).toString()) {
                                case "  ":
                                    cell.setBackground(java.awt.Color.WHITE);
                                    break;
                                case "##":
                                    cell.setBackground(java.awt.Color.BLACK);
                                    break;
                                case "[]":
                                    cell.setBackground(java.awt.Color.GREEN);
                                    break;
                            }
                        }
                    }
                }
            }
        });
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
