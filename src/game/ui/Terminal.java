package game.ui;

import java.io.Console;
import java.util.Scanner;

import game.View;
import game.map.Map;
import game.ui.Style;

import static game.ui.Style.*;

public class Terminal implements View{
    public String nom;
    public int chooseMode = -1;
    public int chooseMap = -1;
    public boolean win = false;
    public boolean lose = false;
    public final int longueur = 9;
    public final int largeur = 5;
    private final Scanner scanner = new Scanner(System.in);
    public Terminal(){

        //Demande du nom :
        while(this.nom == null){
            System.out.print(stringBase +"Quel est votre nom ? : " + stringGras + stringCouleurPourpre);
            String nom = scanner.nextLine();
            nom = nom.toUpperCase();
            boolean valid = true;
            for(int i = 0; i < nom.length(); i++){
                if(!(nom.charAt(i) >= 'A' && nom.charAt(i) <= 'Z')) valid = false;
            }
            if(valid) this.nom = nom;
            else System.out.println(stringErrorMessage);
        }

        //Demande du mode choisi :
        while(this.chooseMode == -1){
            System.out.print(stringBase + "Quel mode de jeu souhaité vous ? " + stringGras + stringCouleurCyan + "(1) Normal " + stringBase + " | " + stringGras + stringCouleurRouge + "(2) Marathon " +  stringBase + ": ");
            String chooseMode = scanner.nextLine();
            boolean valid = true;
            if(!chooseMode.equals("1") && !chooseMode.equals("2")){
                valid = false;
            }
            if(valid) this.chooseMode = Integer.valueOf(chooseMode) - 1;
            else System.out.println(stringErrorMessage);
        }

        //Demande du choix de la map :
        while(this.chooseMap == -1){
            System.out.print(stringBase + "Quel est la map souhaité vous ? " + stringGras +stringCouleurVert + "(1) Plaine des Vertus " + stringBase + " | " + stringGras + stringCouleurJaune + "(2) La Voie Royale " +  stringBase + ": ");
            String chooseMap = scanner.nextLine();
            boolean valid = true;
            if(!chooseMap.equals("1") && !chooseMap.equals("2")){
                valid = false;
            }
            if(valid) this.chooseMap = Integer.valueOf(chooseMap) - 1;
            else System.out.println(stringErrorMessage);
        }
    }

    public void play(){
        if(chooseMode == 0) playNormalMode();
        else playMarathonMode();
    }

    private void playNormalMode(){
        System.out.println("Vous êtes en mode Normal :");
        while(!win && !lose){
            int choix = choixNormalAction();

            //Le joueur veut placer une tour
            if(choix == 1){

            }

            //Le joueur veut commencer la vague
            if(choix == 2){

            }

            //Le joueur veut entrer dans l'état Option
            if(choix == 3){
                //Créer une méthode qui met pause au jeu
                quitOrRestart();
            }
        }
        if(win) System.out.println("Vous avez gagné. Félicitation !");
        if(lose) System.out.println("Vous avez perdu :(");
        System.exit(0);
    }

    private void playMarathonMode(){
        System.out.println("Vous êtes en mode marathon");
        while (!lose){
            int choix = -1;
        }
        if(lose) System.out.println("Vous avez tenu " + "Mettre le nombre de vague ici à prendre dans le model" + " vagues !");
        System.exit(0);
    }

    private void quitOrRestart(){
        Scanner scanner = new Scanner(System.in);
        int valid = -1;
        while(valid == -1){
            System.out.print(stringBase + "Voulez vous arreter le jeu ? " + stringCouleurVert + "(1) Oui " + stringBase + " | " + stringCouleurRouge + "(2) Non : ");
            String value = scanner.nextLine();
            if(!value.equals("1") && !value.equals("2")) System.out.println(stringErrorMessage);
            else valid = Integer.valueOf(value);
        }
        if(valid == 2){
            //Créer une méthode pour revenir dans le le jeu.
        }
        else System.exit(0);
    }

    private int choixNormalAction(){
        int choix = -1;
        while(choix == -1){
            System.out.print(stringBase + "Quel action souhaité vous réaliser ? : " + stringCouleurJaune + "(1) Placer une tour" + stringBase + " | " + stringCouleurRouge + "(2) Commencer le tour" + stringBase + " | " + stringCouleurJaune + "(3) Pause" + stringBase + ": " );
            String value = scanner.nextLine();
            if(!value.equals("1") && !value.equals("2") && !value.equals("3")) System.out.println(stringErrorMessage);
            else choix = Integer.valueOf(value);
        }
        return choix;
    }

    public void update(Map map){
        // clear screen
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
        // print map
        System.out.println(map);
    }
    



}
