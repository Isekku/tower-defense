package game.ui.Vue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Tool;

import game.ui.Controller.PlayingController;
import game.map.Cell;
import game.ui.Style;

public class Playing extends JFrame implements State{
    //Tout attributs permettant d'instancier la classe :
    public static boolean isFirstTime = true;
    private static final Playing instance = new Playing();
    private PlayingController controller;
    private final String assetPath = "/resources/assets/";
    public int panelWidth;
    public int panelHeight;

    //Constructeur unique :
    public Playing(){
        controller = new PlayingController(this);
        mapGrid = new JPanel[controller.mapHeight][controller.mapHeight];
        playingPanel = new JPanel();

        try{terreImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(assetPath + "terrain/Tiles/FieldsTile_01.png"))));
            herbeImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(assetPath + "terrain/Tiles/FieldsTile_38.png"))));
            fleurImage = createImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(assetPath + "terrain/Objects/7_Decor/Tree1.png"))));
        }
        catch(IOException e){
            System.out.println("L'asset donné n'est pas la bonne");
        }
        catch(IllegalArgumentException e){
            System.out.println("L'URL donné n'est pas le bon");
            e.printStackTrace();
        }
    }
    private int[][] mapDesign = {
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0}
    };

    //Les Panels et les Components besoins pour l'affichage :
    protected JPanel playingPanel = new JPanel();
        protected JPanel mapPanel = new JPanel();
            protected JPanel mapGridPanel = new JPanel();
                protected JPanel mapGrid[][];


        protected JPanel infoPanel = new JPanel();
            protected JLabel moneyLabel = new JLabel("Money : ");
            protected JLabel lifeLabel = new JLabel("Life : ");
            protected JLabel waveLabel = new JLabel("Wave : ");
            protected JLabel timeLabel = new JLabel("Time : ");
        
        protected JPanel startPanel = new JPanel();
            protected JButton startButton = new JButton("Commencer le tour");
            protected JButton pauseButton = new JButton("Pause");


    public ImageIcon terreImage; //= new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources/assets/terrain/Tiles/FieldsTile_01.png"));

    private ImageIcon herbeImage; //= new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources\\assets\\terrain\\Tiles\\FieldsTile_38.png"));

    public ImageIcon fleurImage;




    //Méthodes propre à la construction de Component plus spécifique :


    public JPanel[][] getMapGrid(){return mapGrid;}

    public JPanel getMapGridPanel(int hauteur, int largeur){
        return mapGrid[hauteur][largeur];
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

        playingPanel.setLayout(new BorderLayout());
            mapPanel.setLayout(new BorderLayout());
                mapGridPanel.setLayout(new GridLayout(mapDesign.length, mapDesign[0].length));
                controller.updateMap();

                mapPanel.add(mapGridPanel, BorderLayout.CENTER);
            playingPanel.add(mapPanel, BorderLayout.CENTER);

            infoPanel.setLayout(new GridLayout(4, 1));
                infoPanel.add(moneyLabel);
                infoPanel.add(lifeLabel);
                infoPanel.add(waveLabel);
                infoPanel.add(timeLabel);
            playingPanel.add(infoPanel, BorderLayout.SOUTH);

            startPanel.setLayout(new GridLayout(1, 3));
                startPanel.add(startButton);
                startButton.addActionListener(e -> {
                    startWave();
                });
                startPanel.add(pauseButton);
                pauseButton.addActionListener(e -> {
                    pauseWave();
                });

            playingPanel.add(startPanel, BorderLayout.NORTH);

        // infiniteMoney();
        System.out.println("Hauteur/Longueur du gridJpanel : " + mapDesign.length + " " + mapDesign[0].length);
        controller.updateMap();

        // controller.updateMap();
        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }

    public void printMap(){
        mapGridPanel.removeAll();
        for(int i = 0; i < mapDesign.length; i++){
            for(int j = 0; j < mapDesign[0].length; j++){
                if(mapDesign[i][j] == 0){
                    mapGrid[i][j] = new JPanel() {
                        protected void paintComponent(Graphics g){
                            super.paintComponent(g);
                            g.drawImage(herbeImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                            if(panelWidth == 0) panelWidth = getWidth();
                            if(panelHeight == 0) panelHeight = getHeight();
                        }
                    };
                }
                else{
                mapGrid[i][j] = new JPanel() {
                    protected void paintComponent(Graphics g){
                        super.paintComponent(g);
                        g.drawImage(terreImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                        if(panelWidth == 0) panelWidth = getWidth();
                        if(panelHeight == 0) panelHeight = getHeight();
                    }
                };}
                mapGrid[i][j].setLayout(new GridLayout(1, 1));
                mapGrid[i][j].add(new JLabel());
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
}
