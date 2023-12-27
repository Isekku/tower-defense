package game.ui.Vue;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Tool;

import game.ui.Controller.PlayingController;
import game.ui.Style;

public class Playing extends JFrame implements State{
    //Tout attributs permettant d'instancier la classe :
    public static boolean isFirstTime = true;
    private static final Playing instance = new Playing();
    private PlayingController controller;
    private final int hauteur = 10;
    private final int largeur = 11;
    private final String assetPath = "/resources/assets/";

    //Constructeur unique :
    public Playing(){
        controller = new PlayingController(this);
        playingPanel = new JPanel();

        try{
            terreImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(assetPath+"terrain/Tiles/FieldsTile_01.png")));
            herbeImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(assetPath+"terrain/Tiles/FieldsTile_38.png")));
        }
        catch(IOException e){
            System.out.println("L'asset donné n'est pas la bonne");
        }
        catch(IllegalArgumentException e){
            System.out.println("L'URL donné n'est pas le bon");
        }
    }

    //Les Panels et les Components besoins pour l'affichage :
    protected JPanel playingPanel = new JPanel();
        protected JPanel mapPanel = new JPanel();
            protected JPanel mapGridPanel = new JPanel();
                protected JPanel mapGrid[][] = new JPanel[hauteur][largeur];


        protected JPanel infoPanel = new JPanel();
            protected JLabel moneyLabel = new JLabel("Money : ");
            protected JLabel lifeLabel = new JLabel("Life : ");
            protected JLabel waveLabel = new JLabel("Wave : ");
            protected JLabel timeLabel = new JLabel("Time : ");
        
        protected JPanel startPanel = new JPanel();
            protected JButton startButton = new JButton("Commencer le tour");
            protected JButton pauseButton = new JButton("Pause");


    private ImageIcon terreImage; //= new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources/assets/terrain/Tiles/FieldsTile_01.png"));

    private ImageIcon herbeImage; //= new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources\\assets\\terrain\\Tiles\\FieldsTile_38.png"));

    private int[][] mapDesign = {
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0}
    };



    //Méthodes propre à la construction de Component plus spécifique :





    //Méthodes permettant d'attribuer les méthodes en fonction d'action produit sur le bouton :

    public void startWave(){
        controller.startWave();
    }



    //Méthodes nécessaires pour la construction de la vue :
    public void enterState() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        controller.refresh();

        playingPanel.setLayout(new BorderLayout());
            mapPanel.setLayout(new BorderLayout());
                mapGridPanel.setLayout(new GridLayout(hauteur, largeur));
                    for(int i = 0; i < hauteur; i++){
                        for(int j = 0; j < largeur; j++){
                            if(mapDesign[i][j] == 0){
                                mapGrid[i][j] = new JPanel() {
                                    protected void paintComponent(Graphics g){
                                        super.paintComponent(g);
                                        g.drawImage(herbeImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                                    }
                                };
                            }
                            else{
                            mapGrid[i][j] = new JPanel() {
                                protected void paintComponent(Graphics g){
                                    super.paintComponent(g);
                                    g.drawImage(terreImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                                }
                            };}
                            mapGridPanel.add(mapGrid[i][j]);
                        }

                    }

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

            playingPanel.add(startPanel, BorderLayout.NORTH);

        infiniteMoney();


        //Instruction permettant d'avoir un affichage correcte dans notre fenêtre :
        refresh();
    }

    public void infiniteMoney() {
        Thread moneyThread = new Thread(() -> {
            while (true) {
                controller.incrementMoney(1);
                refresh();
                try {
                    Thread.sleep(1);
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

    public JLabel getLifeLabel() {
        return lifeLabel;
    }

    public JLabel getWaveLabel() {
        return waveLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

}
