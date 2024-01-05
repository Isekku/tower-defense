package game.ui.Model;

import game.Entity.*;
import game.Game;
import game.GameState;
import game.geometry.Coordinates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel{
    private JLabel entityLabel;
    private Coordinates panelCoordinates;

    public GamePanel(Coordinates c){
        Entity entity = Game.model.getMap().getEntity(c);
        entityLabel = createEntityLabel(entity);

        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(entity != null)
                moveEntity();
            }
        });
        timer.start();
    }

    private JLabel createEntityLabel(Entity entity){
        JLabel label = new JLabel(entity.printTerminal);
        label.setBounds(entity.coordinates.getX() * 20, entity.coordinates.getY() * 20, 20, 20);
        add(label);
        return label;
    }

    private void moveEntity(){
        Entity entity = getEntityFromLabel(entityLabel);
        entity.coordinates.moveRight();

        entityLabel.setBounds(entity.coordinates.getX() * 20, entity.coordinates.getY() * 20, 20, 20);
        repaint();
    }

    private void moveEntityBetweenPanels(Entity entity){
        int x = entity.coordinates.getX();
        int y = entity.coordinates.getY();
    }

    private Entity getEntityFromLabel(JLabel label){
        return Game.model.getMap().getEntity(panelCoordinates);
    }
}
