package game.ui.Vue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Tool;

import game.ui.Controller.PlayingController;
import game.map.Cell;
import game.ui.Model.MapCell;
import game.ui.Model.PlayingPanel;
import game.ui.Style;

public class Playing extends JFrame implements State{
    //Tout attributs permettant d'instancier la classe :
    public static boolean isFirstTime = true;
    private static final Playing instance = new Playing();
    private PlayingController controller;
    private final String assetPath = "/resources/assets/";

    //Constructeur unique :
    public Playing(){
        controller = new PlayingController(this);
        mapGrid = new JPanel[controller.mapHeight][controller.mapWidth];
        mapDesign = new int[controller.mapHeight][controller.mapWidth];
        int rotation = 0;

        for(int i = 0; i < controller.mapHeight; i++){
            for(int j = 0; j < controller.mapWidth; j++){
                mapDesign[i][j] = rotation%2;
                rotation++;
            }
        }
        playingPanel = new JPanel();

        try{
            terreImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(assetPath + "terrain/Tiles/FieldsTile_01.png"))));
            herbeImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(assetPath + "terrain/Tiles/FieldsTile_38.png"))));
            arbreImage = createImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(assetPath + "terrain/Objects/7_Decor/Tree1.png"))));
            slimeImage = new ImageIcon(getClass().getResource(assetPath + "monstres/1/S_Walk.gif")).getImage();
        }
        catch(IOException e){
            System.out.println("L'asset donné n'est pas la bonne");
        }
        catch(IllegalArgumentException e){
            System.out.println("L'URL donné n'est pas le bon");
            e.printStackTrace();
        }
    }
    private int[][] mapDesign;
            /*
            {
            {1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0,1},
    };
             */

    //Les Panels et les Components besoins pour l'affichage :
    protected JPanel playingPanel = new JPanel();
        protected JPanel mapPanel = new JPanel();
            protected JPanel mapGridPanel = new JPanel();
                protected JPanel mapGrid[][];

            protected JPanel towerGridPanel = new JPanel();
                protected JPanel towerGrid[][];

            protected JPanel playingGridPanel = new JPanel(new GridLayout(5, 1));
                protected PlayingPanel[] playingGrid = new PlayingPanel[5];


        protected JPanel infoPanel = new JPanel();
            protected JLabel moneyLabel = new JLabel("Money : ");
            protected JLabel lifeLabel = new JLabel("Life : ");
            protected JLabel waveLabel = new JLabel("Wave : ");
            protected JLabel timeLabel = new JLabel("Time : ");
        
        protected JPanel startPanel = new JPanel();
            protected JButton startButton = new JButton("Commencer le tour");
            protected JButton pauseButton = new JButton("Pause");

        protected JPanel towerPanel = new JPanel();
            protected JLabel basicTowerLabel = new JLabel("Basic Tower");
            protected JLabel electricTowerLabel = new JLabel("Electric Tower");
            protected JLabel iceTowerLabel = new JLabel("Ice Tower");
            protected JLabel royalTowerLabel = new JLabel("Royal Tower");

        protected JPanel towerPlusStartPanel = new JPanel(new GridLayout(2, 1));


    public static ImageIcon terreImage; //= new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources/assets/terrain/Tiles/FieldsTile_01.png"));

    public static ImageIcon herbeImage; //= new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources\\assets\\terrain\\Tiles\\FieldsTile_38.png"));

    public static ImageIcon arbreImage;

    public static Image slimeImage;




    //Méthodes propre à la construction de Component plus spécifique :


    public JPanel[][] getMapGrid(){return mapGrid;}

    public JPanel getTowerGridPanel(int hauteur, int largeur){
        return towerGrid[hauteur][largeur];
    }


    //Méthodes permettant d'attribuer les méthodes en fonction d'action produit sur le bouton :

    public void startWave(){
        controller.startWave();
    }

    public void pauseWave(){
        controller.pauseWave();
    }



    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        controller.refresh();
        towerGrid = new JPanel[controller.mapHeight][controller.mapWidth];

        playingPanel.setLayout(new BorderLayout());
            mapPanel.setLayout(new BorderLayout());
                mapGridPanel.setLayout(new GridLayout(controller.mapHeight, controller.mapWidth));
                towerGridPanel.setLayout(new GridLayout(controller.mapHeight, controller.mapWidth));
                setMap(); newMap();
                controller.updateMap();

                mapPanel.add(mapGridPanel, BorderLayout.CENTER);
                

                for(int i = 0; i < playingGrid.length; i++){
                    playingGrid[i] = new PlayingPanel();
                    playingGrid[i].setOpaque(false);
                    playingGridPanel.add(playingGrid[i]);
                }

            JPanel overlayLayout = new JPanel();
            overlayLayout.setLayout(new OverlayLayout(overlayLayout));

            mapPanel.setOpaque(false);
            playingGridPanel.setOpaque(false);
            towerGridPanel.setOpaque(false);

            overlayLayout.add(playingGridPanel);
            overlayLayout.add(towerGridPanel);
            overlayLayout.add(mapPanel);

            // mettre une map carré
            playingPanel.add(overlayLayout, BorderLayout.CENTER);
            playingPanel.setPreferredSize(new Dimension(100, 100));

            infoPanel.setLayout(new GridLayout(1, 4));
                infoPanel.add(moneyLabel);
                infoPanel.add(lifeLabel);
                infoPanel.add(waveLabel);
                infoPanel.add(timeLabel);
            // playingPanel.add(infoPanel, BorderLayout.SOUTH);

            startPanel.setLayout(new GridLayout(1, 3));
                startPanel.add(startButton);
                startButton.addActionListener(e -> {
                    startWave();
                });
                startPanel.add(pauseButton);
                pauseButton.addActionListener(e -> {
                    pauseWave();
                });

            // playingPanel.add(startPanel, BorderLayout.NORTH);

            towerPanel.setLayout(new GridLayout(1, 4));
                towerPanel.add(basicTowerLabel);
                towerPanel.add(electricTowerLabel);
                towerPanel.add(iceTowerLabel);
                towerPanel.add(royalTowerLabel);
            playingPanel.add(towerPanel, BorderLayout.SOUTH);

            towerPlusStartPanel.add(startPanel);
            towerPlusStartPanel.add(infoPanel);
            playingPanel.add(towerPlusStartPanel, BorderLayout.NORTH);

        // infiniteMoney();
        System.out.println("Hauteur/Longueur du gridJpanel : " + mapDesign.length + " " + mapDesign[0].length);
        controller.updateMap();

        // controller.updateMap();
        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }

    public void setMap(){
        mapGridPanel.removeAll();
        for(int i = 0; i < controller.mapHeight; i++){
            for(int j = 0; j < controller.mapWidth; j++){
                mapGrid[i][j] = new MapCell(mapDesign[i][j]);
                towerGrid[i][j] = new JPanel();
                towerGrid[i][j].setOpaque(false);
                mapGridPanel.add(mapGrid[i][j]);
                towerGridPanel.add(towerGrid[i][j]);
            }

        }
    }

    public void newMap(){
        for(int i = 0; i < controller.mapHeight; i++){
            for(int j = 0; j < controller.mapWidth; j++){
                System.out.println("i : " + i + ". j : " + j);
                towerGrid[i][j].add(new JLabel());
            }
        }
    }


    public void infiniteMoney() {
        Thread moneyThread = new Thread(() -> {
            while (true) {
                controller.incrementMoney(1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        moneyThread.start();
    }

    public void refresh(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller.refresh();
                playingPanel.revalidate();
                playingPanel.repaint();

            }
        });

    }
    
    public void quitState(){
        
    }

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void notFirstTime(){
        if(isFirstTime()) isFirstTime = false;
    }

    private ImageIcon createImageIcon(Image img){
        BufferedImage image = (BufferedImage) img;
        BufferedImage bufferedImage = compatibleImage(image);
        return new ImageIcon(bufferedImage);
    }

    private BufferedImage compatibleImage(BufferedImage image){
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        int transparence = image.getColorModel().getTransparency();
        BufferedImage res = gfxConfig.createCompatibleImage(image.getWidth(), image.getHeight(), transparence);

        Graphics g = res.createGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
        g.dispose();

        return res;
    }

    //Méthodes nécessaire pour l'accessiblité externe de la classe :
    public static Playing getInstance(){
        return instance;
    }

    public JPanel getView() {
        return playingPanel;
    }

    public JLabel getMoneyLabel() {
        return moneyLabel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JLabel getLifeLabel() {
        return lifeLabel;
    }

    public JLabel getWaveLabel() {
        return waveLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public PlayingPanel[] getPlayingGrid(){return this.playingGrid;}
}
