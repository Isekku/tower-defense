package game.ui.Vue;

import game.ui.Controller.ChooseMapController;
import game.ui.Style;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.Flow;

public class ChooseMap extends JFrame implements State {

    //Tout attributs permettant d'instancier la classe :
    protected JPanel chooseMapPanel;
    public static boolean isFirstTime = true;
    private static final ChooseMap instance = new ChooseMap();
    private ChooseMapController controller;

    //Constructeur unique :
    public ChooseMap() {
        controller = new ChooseMapController(this);
        chooseMapPanel = new JPanel(new GridLayout(2, 1));

        try{
            plainImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/bouttons/plaine_des_vertus.jpeg")));
            plainImage = new ImageIcon(plainImage.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));

            plainGrayImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/bouttons/plaine_des_vertus_gris.jpg")));
            plainGrayImage = new ImageIcon(plainGrayImage.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));

            desertImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/bouttons/voie_royale.jpeg")));
            desertImage = new ImageIcon(desertImage.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));

            desertGrayImage = new ImageIcon(ImageIO.read(getClass().getResource("/resources/bouttons/voie_royale_gris.jpg")));
            desertGrayImage = new ImageIcon(desertGrayImage.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
        }
        catch (IOException e){
            System.out.println("Ce n'est pas la bonne image");
        }
        catch (IllegalArgumentException e){
            System.out.println("Vérifier si vous vous n'êtes pas trompé sur l'extension");
        }
    }

    //Les Panels et les Components besoins pour l'affichage :
    JPanel difficultyPanel = new JPanel();
        JPanel difficultyAndDescriptionLabel = new JPanel();
            JLabel difficultyText = new JLabel("Difficulté : ");
            JSlider difficultySlider = new JSlider();
    JPanel chooseTextPanel = new JPanel();
        JLabel chooseText = new JLabel("Choix de Map :");
    JPanel mapChoicePanel = new JPanel();
        JPanel mapPanel = new JPanel();
            JButton map1Panel = new JButton();
                JLabel map1Image = new JLabel();
                ImageIcon plainImage;
                ImageIcon plainGrayImage;
                JLabel map1Title = new JLabel("Plaine des Vertus");
            JButton map2Panel = new JButton();
                JLabel map2Image = new JLabel();
                ImageIcon desertImage;
                ImageIcon desertGrayImage;
                JLabel map2Title = new JLabel("Voie Royale");


    //Méthodes nécessaires pour la construction de la vue ;
    public void enterState(){
        difficultyPanel.setLayout(new BorderLayout());
            difficultyAndDescriptionLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
                difficultyText.setFont(Style.descriptionFont);
                setSlider(difficultySlider);

                difficultyAndDescriptionLabel.add(difficultyText);
                difficultyAndDescriptionLabel.add(difficultySlider);



            difficultyPanel.add(difficultyAndDescriptionLabel, BorderLayout.CENTER);


        mapChoicePanel.setLayout(new BorderLayout());
            chooseTextPanel.setLayout(new BorderLayout());
                chooseText.setFont(Style.descriptionFont);
                chooseText.setHorizontalAlignment(SwingConstants.CENTER);

                chooseTextPanel.add(chooseText, BorderLayout.CENTER);


            mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 90));
                map1Panel.setLayout(new BoxLayout(map1Panel, BoxLayout.X_AXIS));
                map1Panel.setBorder(Style.compound);
                map1Panel.setOpaque(false);
                map1Panel.addActionListener((event) -> controller.mouseClicked(ChooseMapController.PLAIN_MODE));
                map1Panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        controller.mouseIsHovering(map1Panel, map1Image, plainImage, 1);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        controller.mouseIsOut(map1Panel, map1Image, plainGrayImage);
                    }
                });

                    map1Image.setIcon(plainGrayImage);
                    map1Image.setBorder(new EmptyBorder(0, 0, 0, 12));

                    map1Title.setText("Plaine des Vertus");
                    map1Title.setFont(Style.buttonFont);

                    map1Panel.add(map1Image);
                    map1Panel.add(map1Title);

                    mapPanel.add(map1Panel);

                map2Panel.setLayout(new BoxLayout(map2Panel, BoxLayout.X_AXIS));
                map2Panel.setBorder(Style.compound);
                map2Panel.setOpaque(false);
                map2Panel.addActionListener((event) -> controller.mouseClicked(ChooseMapController.DESERT_MODE));
                map2Panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        controller.mouseIsHovering(map2Panel, map2Image, desertImage, 2);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        controller.mouseIsOut(map2Panel, map2Image, desertGrayImage);
                    }
                });

                    map2Image.setIcon(desertGrayImage);
                    map2Image.setBorder(new EmptyBorder(0, 0, 0, 12));

                    map2Title.setText("Voie Royale");
                    map2Title.setFont(Style.buttonFont);
                    map2Title.setAlignmentX(SwingConstants.CENTER);

                    map2Panel.add(map2Image);
                    map2Panel.add(map2Title);

                    mapPanel.add(map2Panel);

            mapChoicePanel.add(chooseTextPanel, BorderLayout.NORTH);
            mapChoicePanel.add(mapPanel, BorderLayout.CENTER);


        chooseMapPanel.add(difficultyPanel, BorderLayout.NORTH);
        chooseMapPanel.add(mapChoicePanel, BorderLayout.CENTER);

        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }
    public void refresh(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chooseMapPanel.revalidate();
                chooseMapPanel.repaint();
            }
        });
    }
    public void quitState(){
        System.out.println();
    }
    public boolean isFirstTime(){
        return isFirstTime;
    }
    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    //Méthode propre à la construction de Components plus spécifiques :
    public void setSlider(JSlider slider){
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinimum(0);
        slider.setMaximum(2);
        slider.setValue(0);
        slider.setPreferredSize(new Dimension(300, 100));

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Facile"));
        labelTable.put(1, new JLabel("Moyen"));
        labelTable.put(2, new JLabel("Difficile"));

        slider.setLabelTable(labelTable);
    }

    //Méthodes permettant d'attribuer les méthodes en fonction d'action produit sur le bouton :
    public static void buttonIsHovered(JButton button, JLabel imageLabel, ImageIcon icon, int MODE){
        if(MODE == 1){
            Border line = new LineBorder(new Color(154, 255, 138),5, true);
            Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));

            button.setBorder(compound);
            imageLabel.setIcon(icon);
        }

        if(MODE == 2){
            Border line = new LineBorder(new Color(255, 254, 138),5, true);
            Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));

            button.setBorder(compound);
            imageLabel.setIcon(icon);
        }
    }
    public static void buttonIsNormal(JButton button, JLabel imageLabel, ImageIcon icon){
            Border line = new LineBorder(Color.BLACK,5, true);
            Border compound = new CompoundBorder(line, new EmptyBorder(5, 5, 5, 5));

            button.setBorder(compound);
            imageLabel.setIcon(icon);
    }

    //Méthode nécessaire pour l'accessibilité externe de la classe :
    public static ChooseMap getInstance(){
        return instance;
    }
    public JPanel getView(){
        return this.chooseMapPanel;
    }
}
