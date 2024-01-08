package game.ui.Model;

import game.Entity.*;
import game.Entity.Mobs.Mob;
import game.Entity.Mobs.NormalMob;
import game.Entity.Mobs.StrongerMob;
import game.Entity.Mobs.WeakMob;
import game.Game;
import game.GameState;
import game.geometry.Coordinates;
import game.ui.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayingPanel extends JPanel{
    private Timer timer;
    private ArrayList<Entity> entityOnPanel;
    private ArrayList<Entity> entityDead;

    public PlayingPanel(){
        entityOnPanel = new ArrayList<>();
        entityDead = new ArrayList<>();
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Entity e : entityOnPanel){
                    if(e.coordinates.getY() + Entity.initializeImage(e.currentImage).getWidth(null) <= 0){
                        entityDead.add(e);
                    }
                }
                for(Entity e : entityDead){
                    entityOnPanel.remove(e);
                }
                entityDead.clear();

                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Entity e : entityOnPanel){
            int centerY = getHeight()/2 - Entity.initializeImage(e.currentImage).getHeight(null) / 2;
            drawMob(g, e.currentImage, (e.coordinates.getYAsFloat()*getWidth() /9) - 150, centerY);
        }
    }

    private void drawMob(Graphics g, String entityImage, float x, int y){
        g.drawImage(Entity.initializeImage(entityImage), (int) x, y, this);
    }

    public void addEntity(Entity entity){
        entityOnPanel.add(entity);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Playing Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        Entity mob = new WeakMob(new Coordinates(0, 10, 0.45f));

        PlayingPanel playingPanel = new PlayingPanel();
        JPanel gridPanel = new JPanel(new GridLayout(5, 1));
        PlayingPanel[] plateau = new PlayingPanel[5];
        for(int i = 0; i < plateau.length; i++){
            plateau[i] = new PlayingPanel();
            plateau[i].setBorder(Style.compound);
            gridPanel.add(plateau[i]);
        }

        frame.add(gridPanel);
        frame.setVisible(true);

        Coordinates c = new Coordinates(10f, plateau[0].getWidth(), 0.45f);
        Entity entity = new NormalMob(c);
        plateau[0].addEntity(entity);

        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                c.moveLeft();
            }
        }).start();

        try{
            Thread.sleep(10000);
            playingPanel.addEntity(new StrongerMob(new Coordinates(10f, playingPanel.getWidth(), 0.45f)));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
