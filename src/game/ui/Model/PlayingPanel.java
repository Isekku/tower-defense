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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timer = new Timer(16, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        for(Entity e : entityOnPanel){
                            if(e.coordinates.getY() + Entity.initializeImage(e.currentImage).getWidth(null) <= 0 || e.getPv() <= 0){
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
        });
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Entity e : entityOnPanel){
            int centerY = getHeight()/2 - Entity.initializeImage(e.currentImage).getHeight(null) / 2;
            drawMob(g, e.currentImage, (e.coordinates.getYAsFloat()*getWidth() /9), centerY);
        }
    }

    private void drawMob(Graphics g, String entityImage, float x, int y){
        g.drawImage(Entity.initializeImage(entityImage), (int) x, y, this);
    }

    public void addEntity(Entity entity){
        entityOnPanel.add(entity);
    }
}
