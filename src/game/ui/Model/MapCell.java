package game.ui.Model;

import game.Entity.Entity;
import game.ui.Vue.Playing;

import javax.swing.*;
import java.awt.*;

public class MapCell extends JPanel {
    private String terrainType; //0 pour herbe, 1 pour terre

    public MapCell(String terrainType){
        this.terrainType = terrainType;
        this.setOpaque(false);
        System.out.println(terrainType);
    }

    protected void paintComponent(Graphics g){
        g.drawImage(Entity.initializeImage("resources/assets/terrain/Tiles/FieldsTile_" + terrainType + ".png"), 0, 0, getWidth(), getHeight(), this);
    }
}
