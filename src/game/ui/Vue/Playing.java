package game.ui.Vue;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
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
import game.Entity.towers.*;
import game.geometry.Coordinates;
import game.ui.Controller.PlayingController;
import game.map.Cell;
import game.ui.Model.MapCell;
import game.ui.Model.PlayingPanel;
import game.ui.Style;

public class Playing extends JFrame implements State{
    //Tout attributs permettant d'instancier la classe :
    public int canPlaceATower = -1;

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

    protected JLabel currentJLabel = null;
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

    public void afficherMenuPause(){
        JPanel menuPause = new JPanel();
        menuPause.setLayout(new GridLayout(4, 1));
        JButton reprendre = new JButton("Reprendre");
        JButton retourMenuPrincipal = new JButton("Retour au menu principal");
        JButton sauvegarder = new JButton("Sauvegarder");
        JButton quitter = new JButton("Quitter");
        menuPause.add(reprendre);
        menuPause.add(retourMenuPrincipal);
        menuPause.add(sauvegarder);
        menuPause.add(quitter);
        menuPause.setBounds(200, 200, 400, 200);
        menuPause.setVisible(true);
        menuPause.setBackground(Color.BLACK);
        menuPause.setForeground(Color.WHITE);
        menuPause.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        menuPause.setOpaque(true);
        reprendre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playingPanel.remove(menuPause);
                playingPanel.revalidate();
                playingPanel.repaint();
                pauseWave();
            }
        });
        retourMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // controller.endGame();
                playingPanel.remove(menuPause);
                playingPanel.revalidate();
                playingPanel.repaint();
            }
        });
        sauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // controller.saveGame();
                playingPanel.remove(menuPause);
                playingPanel.revalidate();
                playingPanel.repaint();
            }
        });
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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



    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        controller.refresh();
        towerGridPanel.setLayout(new GridLayout(controller.mapHeight, controller.mapWidth));
        towerGridPanel.setOpaque(false);
        towerGrid = new JPanel[controller.mapHeight][controller.mapWidth];
        for(int i = 0; i < towerGrid.length; i++){
            for(int j = 0; j < towerGrid[0].length; j++){
                towerGrid[i][j] = new JPanel();
                towerGrid[i][j].setOpaque(false);
                towerGridPanel.add(towerGrid[i][j]);

                int finalI = i;
                int finalJ = j;
                towerGrid[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (canPlaceATower != -1) {
                            boolean placed = false;
                            switch (canPlaceATower){
                                case 0 -> {
                                    placed = controller.addTower(new BasicTower(new Coordinates(finalI, finalJ, 0.15f)));
                                }
                                case 1 ->{
                                    placed = controller.addTower(new ElectricTower(new Coordinates(finalI, finalJ, 0.15f)));
                                }
                                case 2 ->{
                                    placed = controller.addTower(new IceTower(new Coordinates(finalI, finalJ, 0.15f)));
                                }
                                case 3 ->{
                                    placed = controller.addTower(new RoyalTower(new Coordinates(finalI, finalJ, 0.15f)));
                                }
                                default -> {}
                            }
                            if(!placed){
                                towerGrid[finalI][finalJ].setBorder(Style.redLine);
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            Thread.sleep(500);
                                            towerGrid[finalI][finalJ].setBorder(Style.compound);
                                        }
                                        catch (InterruptedException e){
                                            e.printStackTrace();
                                        };
                                    }
                                });
                            }
                            else {
                                towerGrid[finalI][finalJ].setBorder(null);
                                canPlaceATower = -1;
                            }
                            //towerGrid[finalI][finalJ].setBorder(null);
                            //controller.addTower(new BasicTower(new Coordinates(finalI, finalJ, 0.15f)));
                        }
                    }

                    public void mouseEntered(MouseEvent e){
                        if(canPlaceATower != -1)towerGrid[finalI][finalJ].setBorder(Style.compound);
                    }

                    public void mouseExited(MouseEvent e){
                        towerGrid[finalI][finalJ].setBorder(null);
                    }
                });
            }
        }

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

            overlayLayout.add(towerGridPanel);
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

                towerPanel.add(createTowerBuyPanel(basicTowerLabel, 25));
                setMouseEvent(basicTowerLabel, 0);

                towerPanel.add(createTowerBuyPanel(electricTowerLabel, 50));
                setMouseEvent(electricTowerLabel, 1);

                towerPanel.add(createTowerBuyPanel(iceTowerLabel, 75));
                setMouseEvent(iceTowerLabel, 2);

                towerPanel.add(createTowerBuyPanel(royalTowerLabel, 100));
                setMouseEvent(royalTowerLabel, 3);

            playingPanel.add(towerPanel, BorderLayout.SOUTH);
            
            // rajoouter les listener pour toutes les tours



            towerPlusStartPanel.add(startPanel);
            towerPlusStartPanel.add(infoPanel);
            playingPanel.add(towerPlusStartPanel, BorderLayout.NORTH);

        playingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // on vérifie que le clic est bien légal
            }
        });


        // infiniteMoney();
        System.out.println("Hauteur/Longueur du gridJpanel : " + mapDesign.length + " " + mapDesign[0].length);
        controller.updateMap();

        // controller.updateMap();
        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
        controller.startRefreshing();
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

    public void setMouseEvent(JLabel label, int n){
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("j'ai cliqué :)");
                canPlaceATower = n;
            }
        });
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

    private JPanel createTowerBuyPanel(JLabel label, int price){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        panel.add(label);

        JLabel textPrice = new JLabel(price + "pièces");
        textPrice.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(textPrice);
        return panel;
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
