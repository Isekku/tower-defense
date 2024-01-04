package game.ui.Model;

import game.ui.Vue.Playing;

import javax.swing.*;
import java.awt.*;

public class MapCell extends JPanel {
    private int terrainType; //0 pour herbe, 1 pour terre

    public MapCell(int terrainType){
        this.terrainType = terrainType;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(terrainType == 1){
            g.drawImage(Playing.herbeImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        else{
            g.drawImage(Playing.terreImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
