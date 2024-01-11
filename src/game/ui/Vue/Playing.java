package game.ui.Vue;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.tools.Tool;

import game.Entity.Entity;
import game.Entity.towers.BasicTower;
import game.Entity.towers.Tower;
import game.geometry.Coordinates;
import game.ui.Controller.PlayingController;
import game.map.Cell;
import game.ui.Model.MapCell;
import game.ui.Model.PlayingPanel;
import game.ui.Model.TransferableImageIcon;
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
    private String[][] mapDesign = {
            {"06", "35", "35", "35", "35", "35", "35", "12", "38"},
            {"13", "11", "11", "11", "11", "11", "11", "17", "38"},
            {"13", "11", "11", "11", "11", "11", "11", "17", "38"},
            {"13", "11", "11", "11", "11", "11", "11", "17", "38"},
            {"22", "04", "04", "04", "04", "04", "04", "24", "38"},
    };

    private JPanel tourSurSouris = new JPanel();

    //Les Panels et les Components besoins pour l'affichage :
    protected JPanel playingPanel = new JPanel();
        protected JPanel playingResized = new JPanel();
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
            protected JLabel basicTowerLabel = new JLabel(new ImageIcon(Entity.initializeImage("resources/assets/archer/2_Idle/2.gif")));
            protected JLabel electricTowerLabel = new JLabel(new ImageIcon(Entity.initializeImage("resources/assets/archer/2_Idle/3.gif")));
            protected JLabel iceTowerLabel = new JLabel(new ImageIcon(Entity.initializeImage("resources/assets/archer/2_Idle/5.gif")));
            protected JLabel royalTowerLabel = new JLabel(new ImageIcon(Entity.initializeImage("resources/assets/archer/2_Idle/6.gif")));

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

    public void tourSurSouris(int hauteur, int largeur, String towerImage){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(hauteur + " " + largeur);
                tourSurSouris.removeAll();
                tourSurSouris.setLayout(new BorderLayout());
                tourSurSouris.add(new JLabel(new ImageIcon(Entity.initializeImage(towerImage))));
                tourSurSouris.setOpaque(false);
                tourSurSouris.setBounds(largeur * 100, hauteur * 100, 100, 100);
                playingResized.add(tourSurSouris);

            }
        });
        
    }


    //Méthodes permettant d'attribuer les méthodes en fonction d'action produit sur le bouton :

    public void startWave(){
        controller.startWave();
    }

    public void pauseWave(){
        controller.pauseWave();
    }

    public void towerClicked(int tower, String towerImage){
        controller.towerClicked(tower, towerImage);
    }

    public void moovedWithTower(java.awt.event.MouseEvent e){
        controller.moovedWithTower(e);
    }



    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        controller.refresh();
        towerGrid = new JPanel[controller.mapHeight][controller.mapWidth];

        playingPanel.setLayout(new BorderLayout());
            mapPanel.setLayout(new BorderLayout());
                mapGridPanel.setLayout(new GridLayout(controller.mapHeight, controller.mapWidth));
                setMap();
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
            mapGridPanel.setOpaque(false);
            playingGridPanel.setOpaque(false);

            overlayLayout.add(playingGridPanel);
            overlayLayout.add(mapPanel);

            playingResized.setLayout(new FlowLayout(FlowLayout.CENTER));
            playingResized.add(overlayLayout);
            playingResized.setBackground(Style.backgroundColor);
            overlayLayout.setBackground(Style.backgroundColor);
            overlayLayout.setPreferredSize(new Dimension(800, 550));

            // mettre une map carré
            playingPanel.add(playingResized, BorderLayout.CENTER);

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
            towerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
            towerPanel.setBackground(Style.backgroundColor);
                towerPanel.add(basicTowerLabel);
                towerPanel.add(electricTowerLabel);
                towerPanel.add(iceTowerLabel);
                towerPanel.add(royalTowerLabel);
            playingPanel.add(towerPanel, BorderLayout.SOUTH);
            
            // rajoouter les listener pour toutes les tours
            basicTowerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    towerClicked(0, "resources/assets/archer/2_Idle/2.gif");
                }
            });
            electricTowerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    towerClicked(1, "resources/assets/archer/2_Idle/3.gif");
                }
            });
            iceTowerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    towerClicked(2, "resources/assets/archer/2_Idle/5.gif");
                }
            });
            royalTowerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    towerClicked(3, "resources/assets/archer/2_Idle/6.gif");
                }
            });


            towerPlusStartPanel.add(startPanel);
            towerPlusStartPanel.add(infoPanel);
            playingPanel.add(towerPlusStartPanel, BorderLayout.NORTH);

        playingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // on vérifie que le clic est bien légal
            }
        });

        playingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // on bouge la tour quand on bouge la souris
                moovedWithTower(e);
            }
        });

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
                mapGrid[i][j].setOpaque(false);
                mapGridPanel.add(mapGrid[i][j]);
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
